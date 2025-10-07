package pl.wblo.jwt.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wblo.jwt.restobjects.*;
import pl.wblo.logger.MyLogger;
import pl.wblo.repository.UserRepository;
import pl.wblo.repository.entity.JwtUser;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TwoFactorAuthenticationService tfaService;
    
    public AuthenticationService(final UserRepository repository, final PasswordEncoder passwordEncoder, final JwtService jwtService, final AuthenticationManager authenticationManager, final TwoFactorAuthenticationService tfaService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tfaService = tfaService;
    }
    
    public static AuthenticationServiceBuilder builder() {
        return new AuthenticationServiceBuilder();
    }
    
    public void register(RegisterRequestObj request) throws Exception {
        MyLogger.debug("register() is starting");
        var user = createUserFromRequest(request);
        MyLogger.debug("register() after user creating");
        // if MFA enabled --> Generate Secret
//        if (request.isMfaEnabled()) {
//            user.setSecret(tfaService.generateNewSecret());
//        }
        repository.save(user);
        MyLogger.debug("register() after save()");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        MyLogger.debug("register() before the end");
        AuthResponseObj.builder()
                .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
    
    private JwtUser createUserFromRequest(RegisterRequestObj request) {
        JwtUser user = new JwtUser();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().getAuthorities().toString());
        user.setMfa_enabled(request.isMfaEnabled());
        user.setSecret("");
        return user;
    }
    
    public AuthResponseObj authenticate(AuthRequestObj request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
                );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (user.isMfaEnabled()) {
            return AuthResponseObj.builder()
                    .accessToken("")
                    .refreshToken("")
                    .mfaEnabled(true)
                    .build();
        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponseObj.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .mfaEnabled(false)
                .build();
    }
    
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository
                    .findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var refreshResponseObj = RefreshResponseObj.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .mfaEnabled(false)
                        .build();
            }
        }
    }
    
    public VerifyResponseObj verifyCode(VerifyRequestObj verifyRequestObj) {
        UserDetails userDetails = repository
                .findByEmail(verifyRequestObj.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user found with %S")
                );
//        if (tfaService.isOtpNotValid(user.getSecret(), verifyRequestObj.getCode())) {
//            throw new BadCredentialsException("Code is not correct");
//        }
        var jwtToken = jwtService.generateToken(userDetails);
        return VerifyResponseObj.builder()
                .accessToken(jwtToken)
                .mfaEnabled(userDetails.isEnabled())
                .build();
    }
    
    @Override
    public String toString() {
        return "AuthenticationService{" +
                "repository=" + repository +
                ", passwordEncoder=" + passwordEncoder +
                ", jwtService=" + jwtService +
                ", authenticationManager=" + authenticationManager +
                ", tfaService=" + tfaService +
                '}';
    }
    
    public static class AuthenticationServiceBuilder {
        private UserRepository repository;
        private PasswordEncoder passwordEncoder;
        private JwtService jwtService;
        private AuthenticationManager authenticationManager;
        private TwoFactorAuthenticationService tfaService;
        
        AuthenticationServiceBuilder() {
        }
        
        public AuthenticationServiceBuilder repository(UserRepository repository) {
            this.repository = repository;
            return this;
        }
        
        public AuthenticationServiceBuilder passwordEncoder(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            return this;
        }
        
        public AuthenticationServiceBuilder jwtService(JwtService jwtService) {
            this.jwtService = jwtService;
            return this;
        }
        
        public AuthenticationServiceBuilder authenticationManager(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
            return this;
        }
        
        public String toString() {
            return "AuthenticationService.AuthenticationServiceBuilder(repository=" + this.repository + ", passwordEncoder=" + this.passwordEncoder + ", jwtService=" + this.jwtService + ", authenticationManager=" + this.authenticationManager + ", tfaService=" + this.tfaService + ")";
        }
        
        public AuthenticationService build() {
            return new AuthenticationService(this.repository, this.passwordEncoder, this.jwtService, this.authenticationManager, this.tfaService);
        }
    }
}

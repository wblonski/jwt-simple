package pl.wblo.jwt.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.wblo.repository.UserRepository;

import java.util.List;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

//@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {
        "pl.wblo.repository",
        "pl.wblo.jwt.service",
        "pl.wblo.jwt.security.config",
        "pl.wblo.jwt.controller",
        "pl.wblo.logger"
})
@EnableAspectJAutoProxy(proxyTargetClass = false) // Add this annotation
public class ApplicationConfig {
    
    private static final String ALLOWED_ORIGIN = "http://localhost:8080";
    private static final List<String> ALLOWED_HEADERS = List.of(
            ORIGIN,
            CONTENT_TYPE,
            ACCEPT,
            AUTHORIZATION
    );
    private static final List<String> ALLOWED_METHODS = List.of(
            GET.name(),
            POST.name(),
            DELETE.name(),
            PUT.name(),
            PATCH.name()
    );
    private static final String CORS_MAPPING_PATTERN = "/**";
    
    private final UserRepository repository;
    
    public ApplicationConfig(UserRepository repository) {
        this.repository = repository;
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(List.of(ALLOWED_ORIGIN));
        config.setAllowedHeaders(ALLOWED_HEADERS);
        config.setAllowedMethods(ALLOWED_METHODS);
        source.registerCorsConfiguration(CORS_MAPPING_PATTERN, config);
        return new CorsFilter(source);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

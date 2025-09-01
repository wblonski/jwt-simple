import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wblo.jwt.restobjects.*;
import pl.wblo.jwt.security.config.Role;
import pl.wblo.logger.MyLogger;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import static java.net.http.HttpClient.*;
import static java.net.http.HttpResponse.*;

class AuthenticationControllerTest implements IntegrationTest {
    private static final int SUCCESS_STATUS_CODE = 200;
    private static final int EMPTY_RESPONSE_LENGTH = 0;
    private static final String ERROR_MESSAGE_FORMAT = "Error in \"%s\" at timestamp=%s, status code=%s, error=%s, path=%s";
    private static final String EXCEPTION_MESSAGE_FORMAT = "An exception was thrown in \"%s\": %s";
    
    private final String registerUrlStr = "http://localhost:8080/api/v1/auth/register";
    private final String refreshTokenUrlStr = "http://localhost:8080/api/v1/auth/refresh-token";
    private final String authTokenUrlStr = "http://localhost:8080/api/v1/auth/authenticate";
    private final String verifyUrlStr = "http://localhost:8080/api/v1/auth/verify";
    
    private HttpRequest request;
    private HttpResponse<byte[]> response;
    private ErrorResponseObj errRespData;
    private AuthResponseObj authRespData;
    
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    
    @Test
    void returns200_whenUserRegisterWorks() {
        final String firstName = UUID.randomUUID().toString();
        final String lastName = UUID.randomUUID().toString();
        final String email = "%s.%s@domain.com".formatted(firstName, lastName);
        final RegisterRequestObj registerUser = new RegisterRequestObj(firstName, lastName, email, "password", Role.ADMIN, false);
        
        try {
            request = RequestFactory.getUserRegisterRequest(registerUrlStr, registerUser);
            response = newHttpClient().send(request, BodyHandlers.ofByteArray());
            assertSuccessfulResponse(response, "register");
        } catch (Exception e) {
            Assertions.fail(EXCEPTION_MESSAGE_FORMAT.formatted("register", e));
        }
    }
    
    @Test
    void returns200_whenRefreshTokenWorks() {
        try {
            request = RequestFactory.getRefreshTokenRequest(refreshTokenUrlStr);
            response = newHttpClient().send(request, BodyHandlers.ofByteArray());
            
            if (isErrorResponse(response)) {
                handleErrorResponse(response, "refresh-token");
            }
        } catch (Exception e) {
            Assertions.fail(EXCEPTION_MESSAGE_FORMAT.formatted("refresh-token", e));
        }
    }
    
    @Test
    void returns200_whenRegisterAuthenticateAndVerifyWorks() {
        final String firstName = UUID.randomUUID().toString();
        final String lastName = UUID.randomUUID().toString();
        final String email = "%s.%s@domain.com".formatted(firstName, lastName);
        final RegisterRequestObj registerRequestObj = new RegisterRequestObj(firstName, lastName, email, "password", Role.ADMIN, false);
        final AuthRequestObj authRequestObj = new AuthRequestObj(email, "password");
        
        try {
            performRegistration(registerRequestObj);
            String refreshToken = performAuthentication(authRequestObj);
            performVerification(email, refreshToken);
        } catch (Exception e) {
            Assertions.fail(EXCEPTION_MESSAGE_FORMAT.formatted("register-authenticate-verify", e));
        }
    }
    
    private void performRegistration(RegisterRequestObj registerRequestObj) throws Exception {
        request = RequestFactory.getUserRegisterRequest(registerUrlStr, registerRequestObj);
        response = newHttpClient().send(request, BodyHandlers.ofByteArray());
        
        if (isErrorResponse(response)) {
            handleErrorResponse(response, "register");
        }
    }
    
    private String performAuthentication(AuthRequestObj authRequestObj) throws Exception {
        request = RequestFactory.getAuthenticateRequest(authTokenUrlStr, authRequestObj);
        response = newHttpClient().send(request, BodyHandlers.ofByteArray());
        
        if (response.statusCode() == SUCCESS_STATUS_CODE) {
            authRespData = objectMapper.readValue(response.body(), AuthResponseObj.class);
            return authRespData.getRefreshToken();
        } else if (hasResponseBody(response)) {
            handleErrorResponse(response, "authenticate");
        }
        
        return "";
    }
    
    private void performVerification(String email, String refreshToken) throws Exception {
        final VerifyRequestObj verifyRequestObj = new VerifyRequestObj(email, refreshToken);
        request = RequestFactory.getVerifyRequest(verifyUrlStr, verifyRequestObj);
        response = newHttpClient().send(request, BodyHandlers.ofByteArray());
        
        if (response.statusCode() == SUCCESS_STATUS_CODE) {
            VerifyResponseObj verifyResponseObj = objectMapper.readValue(response.body(), VerifyResponseObj.class);
            MyLogger.debug("Verify data:\n access token=%s,\n mfaEnabled=%s"
                                   .formatted(verifyResponseObj.getAccessToken(), verifyResponseObj.getMfaEnabled()));
        } else if (hasResponseBody(response)) {
            handleErrorResponse(response, "verify");
        }
    }
    
    private void assertSuccessfulResponse(HttpResponse<byte[]> response, String operationName) throws Exception {
        if (hasResponseBody(response)) {
            handleErrorResponse(response, operationName);
        }
    }
    
    private boolean isErrorResponse(HttpResponse<byte[]> response) {
        return response.statusCode() != SUCCESS_STATUS_CODE && hasResponseBody(response);
    }
    
    private boolean hasResponseBody(HttpResponse<byte[]> response) {
        return response.body().length != EMPTY_RESPONSE_LENGTH;
    }
    
    private void handleErrorResponse(HttpResponse<byte[]> response, String operationName) throws Exception {
        errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
        Assertions.fail(ERROR_MESSAGE_FORMAT.formatted(
                operationName,
                errRespData.getTimestamp(),
                errRespData.getStatus(),
                errRespData.getError(),
                errRespData.getPath()
        ));
    }

}

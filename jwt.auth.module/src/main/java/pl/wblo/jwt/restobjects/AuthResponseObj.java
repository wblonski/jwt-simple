package pl.wblo.jwt.restobjects;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthResponseObj implements Serializable {
    private String accessToken;
    private String refreshToken;
    private boolean mfaEnabled;
    private String secretImageUri;
    
    public AuthResponseObj(String accessToken, String refreshToken, boolean mfaEnabled, String secretImageUri) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mfaEnabled = mfaEnabled;
        this.secretImageUri = secretImageUri;
    }
    
    public AuthResponseObj() {
    }
    
    public static AuthResponseObjBuilder builder() {
        return new AuthResponseObjBuilder();
    }
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public String getRefreshToken() {
        return this.refreshToken;
    }
    
    public boolean isMfaEnabled() {
        return this.mfaEnabled;
    }
    
    public String getSecretImageUri() {
        return this.secretImageUri;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
    
    public void setSecretImageUri(String secretImageUri) {
        this.secretImageUri = secretImageUri;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthResponseObj)) return false;
        final AuthResponseObj other = (AuthResponseObj) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accessToken = this.getAccessToken();
        final Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken))
            return false;
        final Object this$refreshToken = this.getRefreshToken();
        final Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken))
            return false;
        if (this.isMfaEnabled() != other.isMfaEnabled()) return false;
        final Object this$secretImageUri = this.getSecretImageUri();
        final Object other$secretImageUri = other.getSecretImageUri();
        if (this$secretImageUri == null ? other$secretImageUri != null : !this$secretImageUri.equals(other$secretImageUri))
            return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AuthResponseObj;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        final Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        result = result * PRIME + (this.isMfaEnabled() ? 79 : 97);
        final Object $secretImageUri = this.getSecretImageUri();
        result = result * PRIME + ($secretImageUri == null ? 43 : $secretImageUri.hashCode());
        return result;
    }
    
    public String toString() {
        return "AuthResponseObj(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", mfaEnabled=" + this.isMfaEnabled() + ", secretImageUri=" + this.getSecretImageUri() + ")";
    }
    
    public static class AuthResponseObjBuilder {
        private String accessToken;
        private String refreshToken;
        private boolean mfaEnabled;
        private String secretImageUri;
        
        AuthResponseObjBuilder() {
        }
        
        public AuthResponseObjBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
        
        public AuthResponseObjBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }
        
        public AuthResponseObjBuilder mfaEnabled(boolean mfaEnabled) {
            this.mfaEnabled = mfaEnabled;
            return this;
        }
        
        public AuthResponseObjBuilder secretImageUri(String secretImageUri) {
            this.secretImageUri = secretImageUri;
            return this;
        }
        
        public AuthResponseObj build() {
            return new AuthResponseObj(this.accessToken, this.refreshToken, this.mfaEnabled, this.secretImageUri);
        }
        
        public String toString() {
            return "AuthResponseObj.AuthResponseObjBuilder(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", mfaEnabled=" + this.mfaEnabled + ", secretImageUri=" + this.secretImageUri + ")";
        }
    }
}

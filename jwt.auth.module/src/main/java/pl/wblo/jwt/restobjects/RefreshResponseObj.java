package pl.wblo.jwt.restobjects;

import java.io.Serializable;

public class RefreshResponseObj implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Boolean mfaEnabled;
    
    public RefreshResponseObj(String accessToken, String refreshToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mfaEnabled = mfaEnabled;
    }
    
    public RefreshResponseObj() {
    }
    
    public static RefreshResponseObjBuilder builder() {
        return new RefreshResponseObjBuilder();
    }
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public String getRefreshToken() {
        return this.refreshToken;
    }
    
    public Boolean getMfaEnabled() {
        return this.mfaEnabled;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public void setMfaEnabled(Boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RefreshResponseObj)) return false;
        final RefreshResponseObj other = (RefreshResponseObj) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accessToken = this.getAccessToken();
        final Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken))
            return false;
        final Object this$refreshToken = this.getRefreshToken();
        final Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken))
            return false;
        final Object this$mfaEnabled = this.getMfaEnabled();
        final Object other$mfaEnabled = other.getMfaEnabled();
        if (this$mfaEnabled == null ? other$mfaEnabled != null : !this$mfaEnabled.equals(other$mfaEnabled))
            return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RefreshResponseObj;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        final Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        final Object $mfaEnabled = this.getMfaEnabled();
        result = result * PRIME + ($mfaEnabled == null ? 43 : $mfaEnabled.hashCode());
        return result;
    }
    
    public String toString() {
        return "RefreshResponseObj(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", mfaEnabled=" + this.getMfaEnabled() + ")";
    }
    
    public static class RefreshResponseObjBuilder {
        private String accessToken;
        private String refreshToken;
        private Boolean mfaEnabled;
        
        RefreshResponseObjBuilder() {
        }
        
        public RefreshResponseObjBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
        
        public RefreshResponseObjBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }
        
        public RefreshResponseObjBuilder mfaEnabled(Boolean mfaEnabled) {
            this.mfaEnabled = mfaEnabled;
            return this;
        }
        
        public RefreshResponseObj build() {
            return new RefreshResponseObj(this.accessToken, this.refreshToken, this.mfaEnabled);
        }
        
        public String toString() {
            return "RefreshResponseObj.RefreshResponseObjBuilder(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", mfaEnabled=" + this.mfaEnabled + ")";
        }
    }
}

package pl.wblo.jwt.restobjects;

import java.io.Serializable;

public class VerifyResponseObj implements Serializable {
    private String accessToken;
    private Boolean mfaEnabled;
    
    public VerifyResponseObj(String accessToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.mfaEnabled = mfaEnabled;
    }
    
    public VerifyResponseObj() {
    }
    
    public static VerifyResponseObjBuilder builder() {
        return new VerifyResponseObjBuilder();
    }
    
    public String getAccessToken() {
        return this.accessToken;
    }
    
    public Boolean getMfaEnabled() {
        return this.mfaEnabled;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public void setMfaEnabled(Boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof VerifyResponseObj)) return false;
        final VerifyResponseObj other = (VerifyResponseObj) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accessToken = this.getAccessToken();
        final Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken))
            return false;
        final Object this$mfaEnabled = this.getMfaEnabled();
        final Object other$mfaEnabled = other.getMfaEnabled();
        if (this$mfaEnabled == null ? other$mfaEnabled != null : !this$mfaEnabled.equals(other$mfaEnabled))
            return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VerifyResponseObj;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        final Object $mfaEnabled = this.getMfaEnabled();
        result = result * PRIME + ($mfaEnabled == null ? 43 : $mfaEnabled.hashCode());
        return result;
    }
    
    public String toString() {
        return "VerifyResponseObj(accessToken=" + this.getAccessToken() + ", mfaEnabled=" + this.getMfaEnabled() + ")";
    }
    
    public static class VerifyResponseObjBuilder {
        private String accessToken;
        private Boolean mfaEnabled;
        
        VerifyResponseObjBuilder() {
        }
        
        public VerifyResponseObjBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
        
        public VerifyResponseObjBuilder mfaEnabled(Boolean mfaEnabled) {
            this.mfaEnabled = mfaEnabled;
            return this;
        }
        
        public VerifyResponseObj build() {
            return new VerifyResponseObj(this.accessToken, this.mfaEnabled);
        }
        
        public String toString() {
            return "VerifyResponseObj.VerifyResponseObjBuilder(accessToken=" + this.accessToken + ", mfaEnabled=" + this.mfaEnabled + ")";
        }
    }
}

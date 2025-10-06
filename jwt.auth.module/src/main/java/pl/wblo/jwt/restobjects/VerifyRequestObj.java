package pl.wblo.jwt.restobjects;

import java.io.Serializable;

//@Data
public class VerifyRequestObj implements Serializable {
    private String email;
    private String code;
    
    public VerifyRequestObj(String email, String code) {
        this.email = email;
        this.code = code;
    }
    
    public VerifyRequestObj() {
    }
    
    public static VerifyRequestObjBuilder builder() {
        return new VerifyRequestObjBuilder();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public static class VerifyRequestObjBuilder {
        private String email;
        private String code;
        
        VerifyRequestObjBuilder() {
        }
        
        public VerifyRequestObjBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public VerifyRequestObjBuilder code(String code) {
            this.code = code;
            return this;
        }
        
        public VerifyRequestObj build() {
            return new VerifyRequestObj(this.email, this.code);
        }
        
        public String toString() {
            return "VerifyRequestObj.VerifyRequestObjBuilder(email=" + this.email + ", code=" + this.code + ")";
        }
    }
}

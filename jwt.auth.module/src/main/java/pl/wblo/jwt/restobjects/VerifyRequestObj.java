package pl.wblo.jwt.restobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Builder
public class VerifyRequestObj implements Serializable {
    private String email;
    private String code;
    
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
}

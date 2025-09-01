package pl.wblo.jwt.simple.restobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wblo.jwt.simple.security.config.Role;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequestObj implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private boolean mfaEnabled;
}



package pl.wblo.jwt.simple.restobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequestObj implements Serializable {

    private String email;
    private String password;

}

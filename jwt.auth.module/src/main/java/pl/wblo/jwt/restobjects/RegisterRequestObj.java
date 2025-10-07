package pl.wblo.jwt.restobjects;

import pl.wblo.jwt.security.config.Role;

import java.io.Serializable;

public class RegisterRequestObj implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private boolean mfaEnabled;
    
    public RegisterRequestObj(String firstname, String lastname, String email, String password, Role role, boolean mfaEnabled) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mfaEnabled = mfaEnabled;
    }
    
    public RegisterRequestObj() {
    }
    
    public static RegisterRequestObjBuilder builder() {
        return new RegisterRequestObjBuilder();
    }
    
    public String getFirstname() {
        return this.firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public Role getRole() {
        return this.role;
    }
    
    public boolean isMfaEnabled() {
        return this.mfaEnabled;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterRequestObj)) return false;
        final RegisterRequestObj other = (RegisterRequestObj) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        if (this.isMfaEnabled() != other.isMfaEnabled()) return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RegisterRequestObj;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        result = result * PRIME + (this.isMfaEnabled() ? 79 : 97);
        return result;
    }
    
    public String toString() {
        return "RegisterRequestObj(firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ", mfaEnabled=" + this.isMfaEnabled() + ")";
    }
    
    public static class RegisterRequestObjBuilder {
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private Role role;
        private boolean mfaEnabled;
        
        RegisterRequestObjBuilder() {
        }
        
        public RegisterRequestObjBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        
        public RegisterRequestObjBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }
        
        public RegisterRequestObjBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public RegisterRequestObjBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public RegisterRequestObjBuilder role(Role role) {
            this.role = role;
            return this;
        }
        
        public RegisterRequestObjBuilder mfaEnabled(boolean mfaEnabled) {
            this.mfaEnabled = mfaEnabled;
            return this;
        }
        
        public RegisterRequestObj build() {
            return new RegisterRequestObj(this.firstname, this.lastname, this.email, this.password, this.role, this.mfaEnabled);
        }
        
        public String toString() {
            return "RegisterRequestObj.RegisterRequestObjBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ", mfaEnabled=" + this.mfaEnabled + ")";
        }
    }
}



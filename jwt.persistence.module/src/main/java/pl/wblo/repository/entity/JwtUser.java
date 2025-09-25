package pl.wblo.repository.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "jwt_user", schema = "public")
public class JwtUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String email = "";
    @Column(nullable = false)
    private String password = "";
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false)
    private boolean mfa_enabled = true;
    @Column
    private String role;
    @Column
    private String secret;
    
    public JwtUser(Integer id, String email, String password, String firstName, String lastName, boolean mfa_enabled,
                   String role, String secret) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mfa_enabled = mfa_enabled;
        this.role = role;
        this.secret = secret;
    }
    
    public JwtUser() {
    }
    
    public static JwtUserBuilder builder() {
        return new JwtUserBuilder();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    
    public Boolean isMfaEnabled() {
        return mfa_enabled;
    }
    
    public String getSecret() {
        return secret;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public boolean isMfa_enabled() {
        return this.mfa_enabled;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setMfa_enabled(boolean mfa_enabled) {
        this.mfa_enabled = mfa_enabled;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JwtUser)) return false;
        final JwtUser other = (JwtUser) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        if (this.isMfa_enabled() != other.isMfa_enabled()) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$secret = this.getSecret();
        final Object other$secret = other.getSecret();
        if (this$secret == null ? other$secret != null : !this$secret.equals(other$secret)) return false;
        return true;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof JwtUser;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        result = result * PRIME + (this.isMfa_enabled() ? 79 : 97);
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $secret = this.getSecret();
        result = result * PRIME + ($secret == null ? 43 : $secret.hashCode());
        return result;
    }
    
    public String toString() {
        return "JwtUser(id=" + this.getId() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", mfa_enabled=" + this.isMfa_enabled() + ", role=" + this.getRole() + ", secret=" + this.getSecret() + ")";
    }
    
    public static class JwtUserBuilder {
        private Integer id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private boolean mfa_enabled;
        private String role;
        private String secret;
        
        JwtUserBuilder() {
        }
        
        public JwtUserBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        
        public JwtUserBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public JwtUserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public JwtUserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public JwtUserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public JwtUserBuilder mfa_enabled(boolean mfa_enabled) {
            this.mfa_enabled = mfa_enabled;
            return this;
        }
        
        public JwtUserBuilder role(String role) {
            this.role = role;
            return this;
        }
        
        public JwtUserBuilder secret(String secret) {
            this.secret = secret;
            return this;
        }
        
        public JwtUser build() {
            return new JwtUser(this.id, this.email, this.password, this.firstName, this.lastName, this.mfa_enabled, this.role, this.secret);
        }
        
        public String toString() {
            return "JwtUser.JwtUserBuilder(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", mfa_enabled=" + this.mfa_enabled + ", role=" + this.role + ", secret=" + this.secret + ")";
        }
        
    }
}

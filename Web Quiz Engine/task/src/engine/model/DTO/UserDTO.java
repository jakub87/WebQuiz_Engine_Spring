package engine.model.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class UserDTO {

    @Pattern(regexp = ".{1,}[@].{1,}[.].{1,}")
    String email;

    @Length(min = 5)
    String password;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

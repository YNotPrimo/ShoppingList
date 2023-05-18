package primo.shoppinglist.data.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }

    @Length(min = 3, max = 20, message = "The username must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Length(min = 3, max = 20, message = "The password must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Length(min = 3, max = 20, message = "The password must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
    @Email(message = "Invalid email")
    @NotBlank(message = "The field must not be empty")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}

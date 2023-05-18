package primo.shoppinglist.data.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import primo.shoppinglist.data.binding.UserRegisterBindingModel;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;

    public UserServiceModel() {
    }

    @Length(min = 3, max = 20, message = "The username must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Length(min = 3, max = 20, message = "The password must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }
    @Email(message = "Invalid email")
    @NotBlank(message = "The field must not be empty")
    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }
}

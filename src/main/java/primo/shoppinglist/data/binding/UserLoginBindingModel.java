package primo.shoppinglist.data.binding;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Length(min = 3, max = 20, message = "The username must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
    @Length(min = 3, max = 20, message = "The password must be at least 3 and at most 20 characters long")
    @NotBlank(message = "The field must not be empty")
    public String getPassword() {
        return password;
    }


    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}

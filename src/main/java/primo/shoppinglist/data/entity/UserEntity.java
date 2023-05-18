package primo.shoppinglist.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "User")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;


    @Column(unique = true)
    @Length(min = 3, max = 20, message = "The username must be at least 3 and at most 20 characters long")
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column
    @Length(min = 3, max = 20, message = "The password must be at least 3 and at most 20 characters long")
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(unique = true)
    @Email(message = "Invalid password")
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}

package primo.shoppinglist.service;

import org.springframework.validation.ObjectError;
import primo.shoppinglist.data.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void register(UserServiceModel model);

    String exportErrorMessages(List<ObjectError> allErrors);

    UserServiceModel findByUsernameAndPassword(String username, String password);
}

package primo.shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import primo.shoppinglist.data.entity.UserEntity;
import primo.shoppinglist.data.service.UserServiceModel;
import primo.shoppinglist.repository.UserRepository;
import primo.shoppinglist.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void register(UserServiceModel model) {
        userRepository.saveAndFlush(mapper.map(model, UserEntity.class));
    }

    @Override
    public String exportErrorMessages(List<ObjectError> allErrors) {
        StringBuilder sb = new StringBuilder();
        allErrors.forEach(e -> sb
                        .append(e.getDefaultMessage())
                        .append(System.lineSeparator())
        );
        return sb.toString();
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        UserEntity found = userRepository.findByUsernameAndPassword(username, password);

        if (found == null) {
            return null;
        }
        return mapper.map(found, UserServiceModel.class);
    }
}

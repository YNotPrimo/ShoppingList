package primo.shoppinglist.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entity.UserEntity;
import primo.shoppinglist.data.service.UserServiceModel;
import primo.shoppinglist.repository.UserRepository;
import primo.shoppinglist.service.UserService;

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
}

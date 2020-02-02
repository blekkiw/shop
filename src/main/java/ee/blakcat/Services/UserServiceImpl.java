package ee.blakcat.Services;

import ee.blakcat.Models.User;
import ee.blakcat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseAbstractService <UserRepository, User, String> implements UserService {
    public UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}

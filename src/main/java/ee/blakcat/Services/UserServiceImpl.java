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


    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findBySession(String session) {
        return userRepository.findBySession(session);
    }

    @Override
    public User findByLoginAdnPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login,password);
    }
}

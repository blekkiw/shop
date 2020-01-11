package ee.blakcat.Services;

import com.google.common.base.Strings;
import ee.blakcat.Models.User;
import ee.blakcat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByID(String s) {
        if (Strings.isNullOrEmpty(s)) throw  new RuntimeException("String Empty");
        return userRepository.getByID(s);
    }

    public User save(User ent) {
        return userRepository.save(ent);
    }

    public Set<User> getAll() {
        return userRepository.getAll();
    }
}

package ee.blakcat.Services;

import ee.blakcat.Models.User;
import ee.blakcat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    public UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String login, String password, HttpSession httpSession) {
        final User user = userRepository.findByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        } else {
            user.setSession(httpSession.getId());
            userRepository.save(user);
            return true;
        }

    }
}

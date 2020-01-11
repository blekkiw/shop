package ee.blakcat.Controllers;

import ee.blakcat.Models.User;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class UserControllerToWeb implements UserController {

public UserService userService;

@Autowired
    public UserControllerToWeb(UserService userService) {
        this.userService = userService;
    }

    public User getByID(String s) {
        return null;
    }

    public User save(User ent) {
        return null;
    }

    public Set<User> getAll() {
        return null;
    }

    @GetMapping ("/user")
    public String getTest (Model model) {
    User user = new User("login","pass","name","addr");
    model.addAttribute("user", user);
        System.out.println("Works!");
    return "oneusertest";
    }
}

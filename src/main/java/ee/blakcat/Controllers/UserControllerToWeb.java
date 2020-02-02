package ee.blakcat.Controllers;

import ee.blakcat.Models.User;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class UserControllerToWeb implements UserController {

public UserService userService;

@Autowired
    public UserControllerToWeb(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/getuserbyid")

    @Override
    public String getByID(@RequestParam ("id") String s, Model model) {
    User user = userService.getByID(s);
    model.addAttribute("user", user);
        return "oneuser";
    }

    @PostMapping ("/saveuser")
    public String save (@RequestParam ("login") String login, @RequestParam ("password") String password, @RequestParam ("nameSurname") String nameSurname,
           @RequestParam ("address") String address , Model model) {
    User user = new User(login,password,nameSurname,address);
    userService.save(user);
    model.addAttribute("user", user);
        return "oneuser";
    }

    @GetMapping ("/getall")
    @Override
    public String getAll(Model model) {
    final Set<User> allUsers = userService.getAll();
    model.addAttribute("users", allUsers);
        return "getallusers";
    }
}

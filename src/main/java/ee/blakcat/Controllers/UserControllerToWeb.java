package ee.blakcat.Controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ee.blakcat.Models.User;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.Collections;
import java.util.Set;

@Controller
public class UserControllerToWeb implements UserController {

public UserService userService;

@Autowired
    public UserControllerToWeb(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/user/findid")
    @Override
    public String findById(@RequestParam ("id") String s, Model model) {
    User user = userService.findById(s);
    model.addAttribute("user", user);
        return "user/one";
    }

    @PostMapping ("user/save")
    public String save (@RequestParam ("login") String login, @RequestParam ("password") String password, @RequestParam ("nameSurname") String nameSurname,
           @RequestParam ("address") String address , Model model) {
    User user = new User(login,password,nameSurname,address);
    userService.save(user);
    model.addAttribute("user", user);
        return findById(user.getId(), model);
    }

    @RequestMapping("user/all")
    @Override
    public String findAll(Model model) {
    Set<User> allUsers = userService.findAll();
    model.addAttribute("users", allUsers);
        return "user/all";
    }

    @GetMapping ("/user/findlogin")
    public String findByLogin (@RequestParam ("login") String login, Model model) {
    User user = userService.findByLogin(login);
    model.addAttribute("user", user);
    return "user/one";
    }
}

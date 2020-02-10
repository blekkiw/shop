package ee.blakcat.Controllers;

import ee.blakcat.Models.User;
import ee.blakcat.Models.UserRole;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

@Controller
public class StartAndMenuControllerToWeb {
    UserService userService;

    @Autowired
    public StartAndMenuControllerToWeb(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(HttpSession httpSession, Model model) {
        User user = userService.findBySession(httpSession.getId());
        if (user != null) {
            model.addAttribute("loggedIn", true);
            if (user.getUserRole() == UserRole.ADMINISTRATOR) {
                    model.addAttribute("admin", true);
                } else model.addAttribute("admin", false);
        } else
            model.addAttribute("loggedIn", false);


        return "index";
    }

}

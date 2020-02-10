package ee.blakcat.Controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ee.blakcat.Models.User;
import ee.blakcat.Services.LoginService;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControllerToWeb {
    LoginService loginService;
    UserService userService;
    @Autowired
    public LoginControllerToWeb(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }



@GetMapping ("/logout")
public String logout (HttpSession httpSession, Model model) {
        User user = userService.findBySession(httpSession.getId());
        if (user==null) {
            model.addAttribute("error", "Dont logged in now");
            return "error";
        } else {
            user.setSession(null);
            userService.save(user);
            httpSession.invalidate();
            model.addAttribute("backlink", "/");
            return "/redirect";
        }
}

    @GetMapping ("/loginpage")
    public String loginpage (HttpSession httpSession, Model model) {
        User user = userService.findBySession(httpSession.getId());
    if (user!=null) {
        model.addAttribute("error", "Already logged in");
        return "/error";
    } else return "/loginpage";
    }

    @PostMapping("/login")
    public String login (@RequestParam ("login") String login, @RequestParam ("password") String password, Model model, HttpSession httpSession) {
        if (!loginService.login(login,password, httpSession)) {
            model.addAttribute("error", "Login or password wrong");
            return "/error";
        } else {
            model.addAttribute("backlink", "/");
return "/redirect";
        }
    }
}

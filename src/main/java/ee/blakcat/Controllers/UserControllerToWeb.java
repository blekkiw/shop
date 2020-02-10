package ee.blakcat.Controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ee.blakcat.Models.Cart;
import ee.blakcat.Models.ProcessStatus;
import ee.blakcat.Models.Product;
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
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping ("/user/carts")
    public String userCarts (Model model, HttpSession httpSession) {
    User user = userService.findBySession(httpSession.getId());
    List<Cart> carts = user.getCarts();
        Set <Cart> activeCarts = carts.stream().filter(cart -> cart.getProcessStatus()== ProcessStatus.ACTIVE).collect(Collectors.toSet());
        Set <Cart> anotherCarts = carts.stream().filter(cart -> cart.getProcessStatus()!= ProcessStatus.ACTIVE).collect(Collectors.toSet());

        model.addAttribute("activecarts", activeCarts);
        model.addAttribute("anothercarts", anotherCarts);
    return "/carts/all";
    }

    @GetMapping ("/user/cart")
    public String userCart (Model model, HttpSession httpSession) {
    User user = userService.findBySession(httpSession.getId());
    Cart cart = user.getActiveCart();
        List<Product> products = cart.getProducts();
        HashMap <Product, Integer> productsAtCart = new HashMap<>();
        for (Product product : products) {
         Integer integer = productsAtCart.getOrDefault(product, 0);
         if (integer.equals(0)) {
             productsAtCart.put(product, 1);
         } else {
             integer++;
             productsAtCart.put(product, integer);
         }
        }
        model.addAttribute("atcart", productsAtCart);
    model.addAttribute("cart",cart);
    return "/carts/one";
    }


}

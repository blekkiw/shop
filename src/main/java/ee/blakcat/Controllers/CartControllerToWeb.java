package ee.blakcat.Controllers;

import ee.blakcat.Models.Cart;
import ee.blakcat.Models.ProcessStatus;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.User;
import ee.blakcat.Services.CartService;
import ee.blakcat.Services.ProductService;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CartControllerToWeb implements CartController {
    CartService cartService;
    UserService userService;
    ProductService productService;


    @Autowired
    public CartControllerToWeb(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }







    @Override
    public String findById(String s, Model model, HttpSession httpSession) {
        Cart cart = cartService.findById(s);
        model.addAttribute("cart", cart);
        return "/cart/one";
    }

    @GetMapping ("/cart/add")
    public String addProductToCart (Model model, @RequestParam ("productId")String productId, HttpSession httpSession) {
        User user = userService.findBySession(httpSession.getId());
        if (user==null) {
            model.addAttribute("error", "Please login an try again");
            return "/error";
        } else {
           cartService.addProductToCart (user, productId);
            model.addAttribute("backlink", "/product/find?id="+productId);
            return "/redirect";
        }
    }


    @GetMapping ("/user/cart/deleteproduct")
    public String deleteFromCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
        if (cartService.deleteFromCart(user, id)) {
            model.addAttribute("backlink", "/user/cart");
            return "/redirect";}
        else  {
            model.addAttribute("error", "Something gone wrong");
            return "/error";
        }
    }

    @GetMapping ("/user/cart/deleteallproducts")
    public String deleteAllProductFromCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
        if (cartService.deleteAllProductFromCart(user, id)) {
        model.addAttribute("backlink", "/user/cart");
        return "/redirect";}
        else  {
            model.addAttribute("error", "Something gone wrong");
            return "/error";
        }
    }



    @GetMapping ("/user/cart/deletecart")
    public String deleteCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
     if (cartService.deleteCart(user,id)) {
         model.addAttribute("backlink", "/user/cart");
         return "/redirect";
     } else  {
         model.addAttribute("error", "Something gone wrong");
         return "/error";
     }
    }



    @Override
    public String findAll(Model model) {
        Set <Cart> carts = cartService.findAll();
        Set <Cart> activeCarts = carts.stream().filter(cart -> cart.getProcessStatus()== ProcessStatus.ACTIVE).collect(Collectors.toSet());
        Set <Cart> anotherCarts = carts.stream().filter(cart -> cart.getProcessStatus()!= ProcessStatus.ACTIVE).collect(Collectors.toSet());

        model.addAttribute("activecarts", activeCarts);
        model.addAttribute("anothercarts", anotherCarts);
    return "/cart/all";
    }


}

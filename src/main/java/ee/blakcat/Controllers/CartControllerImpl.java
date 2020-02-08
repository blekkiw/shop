package ee.blakcat.Controllers;

import ee.blakcat.Models.Cart;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.User;
import ee.blakcat.Services.CartService;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CartControllerImpl implements CartController {
    CartService cartService;
    UserService userService;

    @Autowired
    public CartControllerImpl(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }




    @Override
    public String findById(String s, Model model) {
        Cart cart = cartService.findById(s);
        model.addAttribute("cart", cart);
        return "/cart/one";
    }

    @GetMapping ("/cart/new")
    public String add (Model model) {
    Cart cart = new Cart(userService.findById("a1c388e1-c851-4839-9bb2-dcd0faa4dd08"));
    cart.add(new Product());
    cartService.save(cart);
    return null;
    }


    @Override
    public String findAll(Model model) {
        Set <Cart> carts = cartService.findAll();
        model.addAttribute("carts", carts);
    return "cart/all";
    }


}

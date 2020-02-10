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
    public String findById(String s, Model model) {
        Cart cart = cartService.findById(s);
        model.addAttribute("cart", cart);
        return "/cart/one";
    }

    @GetMapping ("/cart/add")
    public String add (Model model, @RequestParam ("productId")String productId, HttpSession httpSession) {
        User user = userService.findBySession(httpSession.getId());
        Product product = productService.findById(productId);
        if (user==null) {
            model.addAttribute("error", "Please login an try again");
            return "/error";
        } else {
           cartService.addProductToCart (user, product);
           userService.save(user);
            model.addAttribute("backlink", "/product/find?id="+productId);
            return "/redirect";
        }
    }


    @GetMapping ("/user/cart/deleteproduct")
    public String deleteFromCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
        Cart cart = user.getActiveCart();
        cart.delete(productService.findById(id));
        userService.save(user);
        model.addAttribute("backlink", "/user/cart");
        return "/redirect";
    }

    @GetMapping ("/user/cart/deleteallproducts")
    public String deleteAllProductFromCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
        Cart cart = user.getActiveCart();
        Product productForDelete = productService.findById(id);
        List<Product> products = cart.getProducts();
        products.removeIf(product -> product.equals(productForDelete));
        userService.save(user);
        model.addAttribute("backlink", "/user/cart");
        return "/redirect";
    }

    @GetMapping ("/user/cart/deletecart")
    public String deleteCart (Model model, HttpSession httpSession, @RequestParam ("id") String id) {
        User user = userService.findBySession(httpSession.getId());
        List <Cart> carts = user.getCarts();
        carts.remove(user.getActiveCart());
        userService.save(user);
        model.addAttribute("backlink", "/user/cart");
        return "/redirect";
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

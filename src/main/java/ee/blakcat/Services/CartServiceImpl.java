package ee.blakcat.Services;

import ee.blakcat.Models.*;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends BaseAbstractService <CartRepository, Cart, String> implements CartService {
    CartRepository cartRepository;
    UserService userService;
    ProductService productService;


    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }






    @Override
    public boolean deleteFromCart (User user, String productId) {
        Cart cart = user.getActiveCart();
        Product product = productService.findById(productId);
        cart.delete(product);
        product.inCome();
        productService.save(product);
        userService.save(user);
        return true;
    }



@Override
    public boolean deleteAllProductFromCart (User user, String productId) {
        Cart cart = user.getActiveCart();
        Product productForDelete = productService.findById(productId);
        List<Product> products = cart.getProducts();
        products.removeIf(product -> {
           if (product.equals(productForDelete)){
              product.inCome();
              productService.save(product);
              return true;
           } return false;
        });
        userService.save(user);
        return true;
    }



@Override
    public boolean deleteCart (User user, String cartId) {
        List <Cart> carts = user.getCarts();
        carts.remove(user.getActiveCart());
        Cart cart = cartRepository.findById(cartId).get();
        cart.getProducts().forEach(Product::inCome);
        cart.setProcessStatus(ProcessStatus.FAILED);
        cartRepository.save(cart);
        userService.save(user);
        return true;
    }



    @Override
    public Cart addProductToCart(User user, String productId) {
        Product product = productService.findById(productId);
    if (product==null||user==null) throw new NullPointerException();
    if (product.getStatus()!= Status.ACTIVE) throw  new NullPointerException("Product not active");
Cart cart = user.getActiveCart();
if (cart==null) cart=new Cart(user);
        cart.add(product);
        product.outCome();
        userService.save(user);
        return cart;
    }
}

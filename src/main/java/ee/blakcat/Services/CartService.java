package ee.blakcat.Services;

import ee.blakcat.Models.Cart;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.User;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Service
public interface CartService extends BaseService <Cart, String>  {
    Cart addProductToCart(User user, String productId);
   boolean deleteCart (User user, String cartId);
  boolean deleteAllProductFromCart (User user, String productId);
  boolean deleteFromCart (User user, String productId);
}

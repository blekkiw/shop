package ee.blakcat.Services;

import ee.blakcat.Models.Cart;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.User;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public interface CartService extends BaseService <Cart, String>  {
    Cart addProductToCart(User user, Product product);
}

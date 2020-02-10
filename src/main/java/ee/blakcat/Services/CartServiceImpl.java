package ee.blakcat.Services;

import ee.blakcat.Models.Cart;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.Status;
import ee.blakcat.Models.User;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends BaseAbstractService <CartRepository, Cart, String> implements CartService {
    CartRepository cartRepository;
@Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public Cart addProductToCart(User user, Product product) {
    if (product==null||user==null||product.getStatus()!= Status.ACTIVE) throw new RuntimeException();
Cart cart = user.getActiveCart();
if (cart==null) cart=new Cart(user);
        cart.add(product);
        product.removed();
        return cart;
    }
}

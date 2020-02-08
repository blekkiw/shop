package ee.blakcat.Services;

import ee.blakcat.Models.Cart;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends BaseAbstractService <CartRepository, Cart, String> implements CartService {
    CartRepository cartRepository;
@Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }



}

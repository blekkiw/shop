package ee.blakcat.Services;

import ee.blakcat.Models.Cart;
import ee.blakcat.Repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public interface CartService extends BaseService <Cart, String>  {
}

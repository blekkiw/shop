package ee.blakcat.Services;

import ee.blakcat.Models.Category;
import ee.blakcat.Models.Product;

import java.util.List;
import java.util.Set;

public interface ProductService extends BaseService <Product, String> {
    Product productInCome (Product product, int count);
    Product productOutCome (Product product, int count);
    Set<Product> findByCategory (Category category);
}

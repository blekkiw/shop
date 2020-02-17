package ee.blakcat.Services;

import ee.blakcat.Models.Category;
import ee.blakcat.Models.Product;
import ee.blakcat.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl extends BaseAbstractService <ProductRepository, Product, String> implements ProductService {

    ProductRepository productRepository;
@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product productInCome(Product product, int count) {
        for (int i = 0; i < count; i++) {
            product.inCome();
        }
    Product product1 = productRepository.save(product);
        return product1;
    }

    @Override
    public Product productOutCome(Product product, int count) {
    if (product.getCountAvailable()<count) throw new NullPointerException("Too many to out");
        for (int i = 0; i < count; i++) {
            product.outCome();
        }
    Product product1 = productRepository.save(product);
        return product1;
    }

    @Override
    public Set<Product> findByCategory(Category category) {
   final Set <Product> products = new HashSet<>();
    productRepository.findByCategory(category).forEach(products::add);
        return products;
    }
}

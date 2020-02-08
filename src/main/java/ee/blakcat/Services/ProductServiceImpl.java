package ee.blakcat.Services;

import ee.blakcat.Models.Product;
import ee.blakcat.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl extends BaseAbstractService <ProductRepository, Product, String> implements ProductService {

    ProductRepository productRepository;
@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}

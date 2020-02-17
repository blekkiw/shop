package ee.blakcat.Repositories;

import ee.blakcat.Models.Category;
import ee.blakcat.Models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository <Product, String> {
    Iterable<Product> findByCategory (Category category);
}

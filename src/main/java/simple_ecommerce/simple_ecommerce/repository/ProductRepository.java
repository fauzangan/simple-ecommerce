package simple_ecommerce.simple_ecommerce.repository;

import simple_ecommerce.simple_ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Product update(Product product);
    Optional<Product> findById(Integer id);
    int deleteById(Integer id);
    List<Product> findAll();
}

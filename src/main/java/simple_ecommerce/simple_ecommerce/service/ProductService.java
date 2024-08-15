package simple_ecommerce.simple_ecommerce.service;

import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.model.User;

import java.util.List;

public interface ProductService {
    Product create(Product request);
    List<Product> getAll();
    Product findById(Integer id);
    Product update(Integer id, Product Request);
    void delete(Integer id);
}

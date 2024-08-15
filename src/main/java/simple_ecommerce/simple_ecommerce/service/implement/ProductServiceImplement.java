package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.repository.ProductRepository;
import simple_ecommerce.simple_ecommerce.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product request) {
        Product product = Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();

        return productRepository.save(request);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product update(Integer id, Product request) {
        Product product = findById(id);
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        return productRepository.update(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}

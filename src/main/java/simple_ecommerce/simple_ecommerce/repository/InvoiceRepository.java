package simple_ecommerce.simple_ecommerce.repository;

import simple_ecommerce.simple_ecommerce.model.Invoice;
import simple_ecommerce.simple_ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
    Invoice update(Invoice invoice);
    Optional<Invoice> findById(Integer id);
    List<Invoice> findByUserId(Integer id);
    int deleteById(Integer id);
    List<Invoice> findAll();
}

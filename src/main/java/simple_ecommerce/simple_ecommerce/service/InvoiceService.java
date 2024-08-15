package simple_ecommerce.simple_ecommerce.service;

import simple_ecommerce.simple_ecommerce.model.Invoice;
import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.utils.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    Invoice create(InvoiceDTO request);
    List<Invoice> getAll();
    Invoice findById(Integer id);
    List<Invoice> findByUserId(Integer id);
    Invoice updateStatus(Integer id, String status);
    Invoice fetchStatus(Integer id);
}

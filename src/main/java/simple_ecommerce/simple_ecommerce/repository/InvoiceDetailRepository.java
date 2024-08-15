package simple_ecommerce.simple_ecommerce.repository;

import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailRepository {
    InvoiceDetail save(InvoiceDetail invoiceDetail);
    InvoiceDetail update(InvoiceDetail invoiceDetail);
    Optional<InvoiceDetail> findById(Integer id);
    int deleteById(Integer id);
    List<InvoiceDetail> findAll();
    List<InvoiceDetail> findByInvoiceId(Integer id);
}

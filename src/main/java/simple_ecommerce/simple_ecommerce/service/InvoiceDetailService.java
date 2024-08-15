package simple_ecommerce.simple_ecommerce.service;

import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailService {
    InvoiceDetail create(InvoiceDetail request);
    List<InvoiceDetail> getAll();
    InvoiceDetail findById(Integer id);
    List<InvoiceDetail> findByInvoiceId(Integer id);
    InvoiceDetail update(Integer id, InvoiceDetail Request);
    void delete(Integer id);
}

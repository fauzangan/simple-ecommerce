package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;
import simple_ecommerce.simple_ecommerce.repository.InvoiceDetailRepository;
import simple_ecommerce.simple_ecommerce.service.InvoiceDetailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImplement implements InvoiceDetailService {

    private final InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public InvoiceDetail create(InvoiceDetail request) {
        InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                .product_id(request.getProduct_id())
                .invoice_id(request.getInvoice_id())
                .quantity(request.getQuantity())
                .amount(request.getAmount())
                .build();

        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public List<InvoiceDetail> getAll() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public InvoiceDetail findById(Integer id) {
        return invoiceDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice Detail not Found"));
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(Integer id) {
        return invoiceDetailRepository.findByInvoiceId(id);
    }

    @Override
    public InvoiceDetail update(Integer id, InvoiceDetail request) {
        InvoiceDetail invoiceDetail = findById(id);
        invoiceDetail.setProduct_id(request.getProduct_id());
        invoiceDetail.setInvoice_id(request.getInvoice_id());
        invoiceDetail.setQuantity(request.getQuantity());
        invoiceDetail.setAmount(request.getAmount());

        return invoiceDetailRepository.update(invoiceDetail);
    }

    @Override
    public void delete(Integer id) {
        invoiceDetailRepository.deleteById(id);
    }
}

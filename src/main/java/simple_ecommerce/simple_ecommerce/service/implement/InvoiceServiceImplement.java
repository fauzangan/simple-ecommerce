package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple_ecommerce.simple_ecommerce.model.Invoice;
import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;
import simple_ecommerce.simple_ecommerce.repository.InvoiceDetailRepository;
import simple_ecommerce.simple_ecommerce.repository.InvoiceRepository;
import simple_ecommerce.simple_ecommerce.repository.ProductRepository;
import simple_ecommerce.simple_ecommerce.request.MidtransRequest;
import simple_ecommerce.simple_ecommerce.response.MidtransResponse;
import simple_ecommerce.simple_ecommerce.security.JwtService;
import simple_ecommerce.simple_ecommerce.service.InvoiceDetailService;
import simple_ecommerce.simple_ecommerce.service.InvoiceService;
import simple_ecommerce.simple_ecommerce.service.MidtransService;
import simple_ecommerce.simple_ecommerce.service.ProductService;
import simple_ecommerce.simple_ecommerce.utils.dto.InvoiceDTO;
import simple_ecommerce.simple_ecommerce.utils.dto.TransactionDetailDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImplement implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailService invoiceDetailService;
    private final ProductService productService;
    private final JwtService jwtService;
    private final MidtransService midtransService;

    @Override
    @Transactional
    public Invoice create(InvoiceDTO request) {
        Invoice invoice = Invoice.builder()
                .user_id(jwtService.getUserAuthenticated().getId())
                .quantity(0)
                .amount(0)
                .status("")
                .va_number("")
                .build();

        Invoice createdInvoice = invoiceRepository.save(invoice);
        List<InvoiceDetail> temp = request.getInvoice_details().stream()
                .map(c -> {
                    c.setInvoice_id(createdInvoice.getId());
                    c.setAmount(c.getQuantity() * productService.findById(c.getProduct_id()).getPrice());
                    return invoiceDetailService.create(c);
                }).collect(Collectors.toList());

        Integer totalPrice = temp.stream()
                .mapToInt(InvoiceDetail::getAmount)
                .sum();

        Integer totalQuantity = temp.stream()
                .mapToInt(InvoiceDetail::getQuantity)
                .sum();

        createdInvoice.setQuantity(totalQuantity);
        createdInvoice.setAmount(totalPrice);
        createdInvoice.setInvoice_details(temp);

        try {
            MidtransRequest midtransRequest = MidtransRequest.builder()
                    .payment_type(request.getPayment_type())
                    .transaction_details(TransactionDetailDTO.builder()
                            .gross_amount(totalPrice)
                            .order_id("INV-" + String.valueOf(createdInvoice.getId()))
                            .build())
                    .bank_transfer(request.getBank_transfer())
                    .build();

            MidtransResponse midtransResponse = midtransService.chargeTransaction(midtransRequest);
            createdInvoice.setVa_number(midtransResponse.getVaNumbers().get(0).getVaNumber());
            createdInvoice.setStatus(midtransResponse.getTransactionStatus());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        invoiceRepository.update(createdInvoice);
        return createdInvoice;
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(Integer id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice Not Found"));
    }

    @Override
    public List<Invoice> findByUserId(Integer id) {
        return invoiceRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public Invoice updateStatus(Integer id, String status) {
        MidtransResponse response = midtransService.updateTransactionStatus(id, status);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice Not Found"));
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findByInvoiceId(id);
        invoice.setStatus(response.getTransactionStatus());
        invoiceRepository.update(invoice);
        invoice.setInvoice_details(invoiceDetails);
        return invoice;
    }

    @Override
    @Transactional
    public Invoice fetchStatus(Integer id) {
        MidtransResponse response = midtransService.fetchTransaction(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice Not Found"));
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findByInvoiceId(id);
        invoice.setStatus(response.getTransactionStatus());
        invoiceRepository.update(invoice);
        invoice.setInvoice_details(invoiceDetails);
        return invoice;
    }
}

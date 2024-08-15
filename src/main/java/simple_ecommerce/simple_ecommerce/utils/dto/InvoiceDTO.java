package simple_ecommerce.simple_ecommerce.utils.dto;

import lombok.*;
import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private String payment_type;
    private TransactionDetailDTO transaction_details;
    private BankTransferDTO bank_transfer;
    private String virtual_number;
    private List<InvoiceDetail> invoice_details;
}

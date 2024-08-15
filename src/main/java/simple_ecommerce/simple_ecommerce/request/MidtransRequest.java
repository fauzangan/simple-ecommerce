package simple_ecommerce.simple_ecommerce.request;

import lombok.*;
import simple_ecommerce.simple_ecommerce.utils.dto.BankTransferDTO;
import simple_ecommerce.simple_ecommerce.utils.dto.TransactionDetailDTO;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MidtransRequest {
    private String payment_type;
    private TransactionDetailDTO transaction_details;
    private BankTransferDTO bank_transfer;
}

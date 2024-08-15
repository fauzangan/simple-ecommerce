package simple_ecommerce.simple_ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Integer id;
    private Integer user_id;
    private Integer quantity;
    private Integer amount;
    private String status;
    private String va_number;
    private List<InvoiceDetail> invoice_details;
}

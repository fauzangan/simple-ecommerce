package simple_ecommerce.simple_ecommerce.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {
    private Integer id;
    private Integer product_id;
    private Integer invoice_id;
    private Integer quantity;
    private Integer amount;
}

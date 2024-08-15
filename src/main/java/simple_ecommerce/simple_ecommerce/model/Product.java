package simple_ecommerce.simple_ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer stock;
    private Integer price;
    @JsonIgnore
    private List<InvoiceDetail> invoice_details;
}

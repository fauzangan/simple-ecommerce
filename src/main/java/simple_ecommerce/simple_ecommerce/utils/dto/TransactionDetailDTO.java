package simple_ecommerce.simple_ecommerce.utils.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    private String order_id;
    private Integer gross_amount;
}

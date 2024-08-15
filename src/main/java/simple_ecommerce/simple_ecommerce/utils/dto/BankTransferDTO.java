package simple_ecommerce.simple_ecommerce.utils.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferDTO {
    private String bank;
}

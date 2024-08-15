package simple_ecommerce.simple_ecommerce.service;

import simple_ecommerce.simple_ecommerce.request.MidtransRequest;
import simple_ecommerce.simple_ecommerce.response.MidtransResponse;

public interface MidtransService {
    MidtransResponse chargeTransaction(MidtransRequest midtransRequest);
    MidtransResponse fetchTransaction(Integer id);
    MidtransResponse updateTransactionStatus(Integer id, String status);
}

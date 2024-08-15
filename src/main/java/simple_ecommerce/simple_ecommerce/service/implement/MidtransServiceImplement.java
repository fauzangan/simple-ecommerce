package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simple_ecommerce.simple_ecommerce.config.MidtransConfig;
import simple_ecommerce.simple_ecommerce.service.MidtransService;
import simple_ecommerce.simple_ecommerce.request.MidtransRequest;
import simple_ecommerce.simple_ecommerce.response.MidtransResponse;

@Service
@RequiredArgsConstructor
public class MidtransServiceImplement implements MidtransService {

    private static final String MIDTRANS_ENDPOINT = "https://api.sandbox.midtrans.com/v2";
    private final MidtransConfig midtransConfig;
    private final RestTemplate restTemplate;

    @Override
    public MidtransResponse chargeTransaction(MidtransRequest midtransRequest) {
        HttpEntity<MidtransRequest> request = new HttpEntity<>(midtransRequest, midtransConfig.getServerHeader());

        ResponseEntity<MidtransResponse> response = restTemplate.exchange(
                MIDTRANS_ENDPOINT+"/charge",
                HttpMethod.POST,
                request,
                MidtransResponse.class
        );

        return response.getBody();
    }

    @Override
    public MidtransResponse fetchTransaction(Integer id) {
        HttpEntity<?> request = new HttpEntity<>(null, midtransConfig.getServerHeader());

        ResponseEntity<MidtransResponse> response = restTemplate.exchange(
                MIDTRANS_ENDPOINT + "/" + "INV-" + id + "/status",
                HttpMethod.GET,
                request,
                MidtransResponse.class
        );

        return response.getBody();
    }

    @Override
    public MidtransResponse updateTransactionStatus(Integer id, String status) {
        HttpEntity<?> request = new HttpEntity<>(null, midtransConfig.getServerHeader());

        ResponseEntity<MidtransResponse> response = restTemplate.exchange(
                MIDTRANS_ENDPOINT + "/" + "INV-" + id + "/" + status,
                HttpMethod.POST,
                request,
                MidtransResponse.class
        );

        return response.getBody();
    }
}

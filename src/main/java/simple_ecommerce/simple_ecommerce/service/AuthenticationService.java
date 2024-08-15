package simple_ecommerce.simple_ecommerce.service;

import org.springframework.http.ResponseEntity;
import simple_ecommerce.simple_ecommerce.request.AuthenticationRequest;
import simple_ecommerce.simple_ecommerce.request.RegisterRequest;
import simple_ecommerce.simple_ecommerce.response.AuthenticationResponse;

public interface AuthenticationService {
    ResponseEntity<?> register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}

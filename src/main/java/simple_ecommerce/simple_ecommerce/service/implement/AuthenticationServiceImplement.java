package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simple_ecommerce.simple_ecommerce.model.User;
import simple_ecommerce.simple_ecommerce.repository.UserRepository;
import simple_ecommerce.simple_ecommerce.request.AuthenticationRequest;
import simple_ecommerce.simple_ecommerce.request.RegisterRequest;
import simple_ecommerce.simple_ecommerce.response.AuthenticationResponse;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.security.JwtService;
import simple_ecommerce.simple_ecommerce.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplement implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        savedUser.setPassword(null);
        return Response.renderJson(
                savedUser,
                "Register Succesfully",
                HttpStatus.CREATED
        );
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isPasswordMatch){
            throw new RuntimeException("Password does not match");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user_id(user.getId())
                .build();
    }
}

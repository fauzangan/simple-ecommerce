package simple_ecommerce.simple_ecommerce.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple_ecommerce.simple_ecommerce.request.AuthenticationRequest;
import simple_ecommerce.simple_ecommerce.request.RegisterRequest;
import simple_ecommerce.simple_ecommerce.response.AuthenticationResponse;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "APIs for User Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created Successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        return Response.renderJson(authenticationService.register(request), "User Created Successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Login a user", description = "Authenticates a user with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Authenticated Successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Credentials", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

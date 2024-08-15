package simple_ecommerce.simple_ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple_ecommerce.simple_ecommerce.model.User;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "APIs for Managing Users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New User Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping
    public ResponseEntity<?> create (
            @RequestBody User request
    ) {
        return Response.renderJson(
                userService.create(request),
                "New User Created",
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Get Users"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping
    public ResponseEntity<?> getAll () {
        return Response.renderJson(
                userService.getAll(),
                "Success Get Users",
                HttpStatus.OK
        );
    }
}


package simple_ecommerce.simple_ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "APIs for Managing Products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product Successfully Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody Product request
    ) {
        return Response.renderJson(
                productService.create(request),
                "Product Successfully Created",
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Products Success"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        return Response.renderJson(
                productService.getAll(),
                "Get Products Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Find a product by ID", description = "Finds a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Product Success"),
            @ApiResponse(responseCode = "404", description = "Product Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Integer id
    ) {
        return Response.renderJson(
                productService.findById(id),
                "Get Product Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Update a product", description = "Updates the details of an existing product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Product Success"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
            @ApiResponse(responseCode = "404", description = "Product Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody Product request
    ) {
        return Response.renderJson(
                productService.update(id, request),
                "Update Product Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete Product Success"),
            @ApiResponse(responseCode = "404", description = "Product Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Integer id
    ) {
        productService.delete(id);
        return Response.renderJson(
                "Delete Product Success",
                HttpStatus.NO_CONTENT
        );
    }
}

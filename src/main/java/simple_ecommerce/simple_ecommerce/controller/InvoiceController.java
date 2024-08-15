package simple_ecommerce.simple_ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple_ecommerce.simple_ecommerce.model.Invoice;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.service.InvoiceService;
import simple_ecommerce.simple_ecommerce.utils.dto.InvoiceDTO;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@Tag(name = "Invoice Controller", description = "APIs for Managing Invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Operation(summary = "Create a new invoice", description = "Creates a new invoice based on the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice Successfully Created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody InvoiceDTO request
    ) {
        return Response.renderJson(
                invoiceService.create(request),
                "Invoice Successfully Created",
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get all invoices", description = "Retrieves all invoices.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Invoices Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        return Response.renderJson(
                invoiceService.getAll(),
                "Get Invoices Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Find an invoice by ID", description = "Finds an invoice by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Invoice Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Invoice Not Found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Integer id
    ) {
        return Response.renderJson(
                invoiceService.findById(id),
                "Get Invoice Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Fetch the status of an invoice", description = "Fetches the current status of an invoice by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch Invoice Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Invoice Not Found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetch(
            @PathVariable Integer id
    ) {
        return Response.renderJson(
                invoiceService.fetchStatus(id),
                "Fetch Invoice Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Update the status of an invoice", description = "Updates the status of an invoice by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Status Invoice Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Invoice Not Found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/update/{id}/{status}")
    public ResponseEntity<?> updateStatus(
            @PathVariable Integer id,
            @PathVariable String status
    ) {
        return Response.renderJson(
                invoiceService.updateStatus(id, status),
                "Update Status Invoice Success",
                HttpStatus.OK
        );
    }

}

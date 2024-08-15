package simple_ecommerce.simple_ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple_ecommerce.simple_ecommerce.response.Response;
import simple_ecommerce.simple_ecommerce.service.InvoiceDetailService;

@RestController
@RequestMapping("/api/invoice-details")
@RequiredArgsConstructor
@Tag(name = "Invoice Detail Controller", description = "APIs for Managing Invoice Details")
public class InvoiceDetailController {

    private final InvoiceDetailService invoiceDetailService;

    @Operation(summary = "Find an invoice detail by ID", description = "Finds an invoice detail by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Invoice Detail Success"),
            @ApiResponse(responseCode = "404", description = "Invoice Detail Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Integer id
    ) {
        return Response.renderJson(
                invoiceDetailService.findById(id),
                "Get Invoice Detail Success",
                HttpStatus.OK
        );
    }

    @Operation(summary = "Find all invoice details by invoice ID", description = "Finds all invoice details associated with a given invoice ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Details Success"),
            @ApiResponse(responseCode = "404", description = "Invoice Not Found"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping("/invoice/{id}")
    public ResponseEntity<?> findByInvoiceId(
            @PathVariable Integer id
    ) {
        return Response.renderJson(
                invoiceDetailService.findByInvoiceId(id),
                "Get Details Success",
                HttpStatus.OK
        );
    }
}

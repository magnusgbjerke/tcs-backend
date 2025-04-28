package com.tcs.onlinestore.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/products")
@Tag(name = "products", description = "the product API")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products or search based on name, customerCategory, productCategory and/or type", description = "Retrieves a list of all products or products searched based on name, customerCategory, productCategory and/or type", tags = {"products"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))})})
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String customerCategory,
            @RequestParam(required = false) String productCategory,
            @RequestParam(required = false) String type) {
        if ((search == null || search.isBlank()) &&
                (customerCategory == null || customerCategory.isBlank()) &&
                (productCategory == null || productCategory.isBlank()) &&
                (type == null || type.isBlank())) {
            return productService.getProducts();
        }

        return productService.getFilteredProducts(search, customerCategory, productCategory, type);
    }

    @Operation(summary = "Get a single product", description = "Retrieves one product.", tags = {"products"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))})})
    @GetMapping(path = "{product}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("product") String id) {
        return productService.getProduct(id);
    }

    @Operation(summary = "Get all valid types", description = "Retrieves all valid types.", tags = {"products"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))})})
    @GetMapping(path = "/valid-types")
    public ResponseEntity<ValidTypesDTO> getValidTypes() {
        return productService.getValidTypes();
    }
}
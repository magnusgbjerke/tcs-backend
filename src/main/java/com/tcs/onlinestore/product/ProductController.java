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

    @Operation(summary = "Get all products", description = "Retrieves a list of all products.", tags = {"products"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))})})
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return productService.getProducts();
    }

    @Operation(summary = "Get a single product", description = "Retrieves one product.", tags = {"products"})
    @ApiResponses(value = {@ApiResponse(
            description = "successfully created",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))})})
    @GetMapping(path = "{product}")
    public ResponseEntity<Product> getProduct(@PathVariable("product") String id) {
        return productService.getProduct(id);
    }
}
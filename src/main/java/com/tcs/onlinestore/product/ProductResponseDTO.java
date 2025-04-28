package com.tcs.onlinestore.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Product")
public class ProductResponseDTO {

    @Schema(example = "velora-houndstooth-blazer-i-regular-fit") // combination of brand and name of product
    private String id;

    @Schema(example = "Houndstooth Blazer i Regular Fit")
    private String name;

    @Schema(example = "Velora") // Velora, Drift & Dune, Noxen, Urban Loom, LuxeRoots
    private String brand;

    @Schema(example = "A stylish and sophisticated houndstooth-patterned blazer with a regular fit, perfect for both casual and formal occasions.")
    private String description;

    @Schema(example = "3.7")
    private BigDecimal rating;

    @Schema(example = "hoodie-axe.jpeg")
    private String image;

    @JsonProperty("stock")
    private List<StockResponseDTO> stockResponseDTO;

    @Schema(example = "shoes")
    private String type;

    @Schema(example = "men")
    private String customerCategory;

    @Schema(example = "footwear")
    private String productCategory;

    @Schema(example = "799")
    private BigDecimal price;
}

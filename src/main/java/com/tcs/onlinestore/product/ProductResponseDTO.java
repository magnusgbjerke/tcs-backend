package com.tcs.onlinestore.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Product")
public class ProductResponseDTO {

    @Schema(example = "velora-houndstooth-blazer-i-regular-fit") // combination of brand and name of product
    private String id;

    @Schema(example = "Velora") // Velora, Drift & Dune, Noxen, Urban Loom, LuxeRoots
    private String brand;

    @Schema(example = "Houndstooth blazer i regular fit")
    private String name;

    @JsonProperty("stock")
    private List<StockResponseDTO> stockResponseDTO;

    @Schema(example = "shoes")
    private String type;

    @Schema(example = "men")
    private String customerCategory;

    @Schema(example = "footwear")
    private String productCategory;
}

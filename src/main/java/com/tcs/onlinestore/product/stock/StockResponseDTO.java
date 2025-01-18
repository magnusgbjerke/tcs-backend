package com.tcs.onlinestore.product.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Schema(name = "Stock")
@AllArgsConstructor
public class StockResponseDTO {

    @Schema(example = "small")
    private String size;

    @Schema(example = "10")
    private int quantity;
}

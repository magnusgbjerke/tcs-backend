package com.tcs.onlinestore.product.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "Stock")
public class StockResponseDTO {

    @Schema(example = "small")
    private String size;

    @Schema(example = "10")
    private int quantity;
}

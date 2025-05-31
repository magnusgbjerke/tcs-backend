package com.tcs.onlinestore.order;

import com.tcs.onlinestore.product.size.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "CreateOrder")
public class CreateOrderDTO {
    @Schema(example = "velora-houndstooth-blazer-i-regular-fit") // combination of brand and name of product
    private String productId;

    @Schema(example = "M")
    private String sizeName;

    @Schema(example = "3")
    private int quantity;
}

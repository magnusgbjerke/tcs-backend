package com.tcs.onlinestore.order.orderline;

import com.tcs.onlinestore.product.ProductResponseDTO;
import com.tcs.onlinestore.product.size.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "OrderLine")
public class OrderLineDTO {
    private ProductResponseDTO product;
    
    @Schema(example = "M")
    private String sizeName;

    @Schema(example = "3")
    private int quantity;
}

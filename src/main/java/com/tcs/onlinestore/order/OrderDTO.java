package com.tcs.onlinestore.order;

import com.tcs.onlinestore.order.orderline.OrderLineDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "Order")
public class OrderDTO {
    @Schema(example = "3")
    private LocalDateTime orderCreated;
    @Schema(example = "false")
    private boolean isPaid;
    private List<OrderLineDTO> orderLines;
}

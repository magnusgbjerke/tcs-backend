package com.tcs.onlinestore.order.orderline;

import com.tcs.onlinestore.order.Order;
import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.size.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order orderInst;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Size size;

    @Column(nullable = false)
    private int quantity;

    public OrderLine(Order orderInst, Product product, Size size, int quantity) {
        this.orderInst = orderInst;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }
}

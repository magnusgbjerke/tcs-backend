package com.tcs.onlinestore.order;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "order_entity") // "order" is a reserved SQL keyword. Table renamed to "order_entity"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID userId; // from Keycloak

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime orderCreated;

    @Column(nullable = false)
    private boolean isPaid;

    public Order(UUID userId, LocalDateTime orderCreated, boolean isPaid) {
        this.userId = userId;
        this.orderCreated = orderCreated;
        this.isPaid = isPaid;
    }
}

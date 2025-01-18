package com.tcs.onlinestore.product;

import com.tcs.onlinestore.product.customerCategory.CustomerCategory;
import com.tcs.onlinestore.product.type.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerCategory", nullable = false)
    private CustomerCategory customerCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type", nullable = false)
    private Type type;

    public Product(String name, CustomerCategory customerCategory, Type type) {
        this.name = name;
        this.customerCategory = customerCategory;
        this.type = type;
    }
    //TODO: Add Price, ImageGroup
}


package com.tcs.onlinestore.product;

import com.tcs.onlinestore.brand.Brand;
import com.tcs.onlinestore.product.customerCategory.CustomerCategory;
import com.tcs.onlinestore.product.type.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerCategory", nullable = false)
    private CustomerCategory customerCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type", nullable = false)
    private Type type;

    public Product(Brand brand, String name, CustomerCategory customerCategory, Type type) {
        String formattedStringBrand = brand.getName().toLowerCase().replaceAll("[,\\s]+", "-");
        String formattedStringName = name.toLowerCase().replaceAll("[,\\s]+", "-");

        this.id = formattedStringBrand + "-" + formattedStringName;
        this.brand = brand;
        this.name = name;
        this.customerCategory = customerCategory;
        this.type = type;
    }
    //TODO: Add Price, ImageGroup
}


package com.tcs.onlinestore.product;

import com.tcs.onlinestore.brand.Brand;
import com.tcs.onlinestore.product.customerCategory.CustomerCategory;
import com.tcs.onlinestore.product.type.Type;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true, scale = 2)
    private BigDecimal rating;

    @Column(nullable = false)
    private String image;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type", nullable = false)
    private Type type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerCategory", nullable = false)
    private CustomerCategory customerCategory;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    public Product(String name, Brand brand, String description, BigDecimal rating, String image,
                   CustomerCategory customerCategory, Type type, BigDecimal price) {
        String formattedStringBrand = brand.getName().toLowerCase().replaceAll("[,\\s]+", "-");
        String formattedStringName = name.toLowerCase().replaceAll("[,\\s]+", "-");

        this.id = formattedStringBrand + "-" + formattedStringName;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.rating = rating;
        this.image = image;
        this.customerCategory = customerCategory;
        this.type = type;
        this.price = price;
    }

    public BigDecimal getPriceAfterDiscount() {
        if (discountPercentage == null || discountPercentage == 0) {
            return price;
        }
        BigDecimal discount = price.multiply(BigDecimal.valueOf(discountPercentage / 100.0));
        return price.subtract(discount);
    }
}

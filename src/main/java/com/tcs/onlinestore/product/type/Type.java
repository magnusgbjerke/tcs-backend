package com.tcs.onlinestore.product.type;


import com.tcs.onlinestore.category.productCategory.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Type {

    @Id
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productCategory", nullable = false)
    private ProductCategory productCategory;

}
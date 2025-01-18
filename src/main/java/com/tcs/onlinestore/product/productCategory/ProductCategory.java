package com.tcs.onlinestore.product.productCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategory {

    @Id
    private String name;

}
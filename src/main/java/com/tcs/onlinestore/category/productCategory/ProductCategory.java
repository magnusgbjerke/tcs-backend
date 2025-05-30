package com.tcs.onlinestore.category.productCategory;

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
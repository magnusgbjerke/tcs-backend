package com.tcs.onlinestore.category.customerCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCategory {

    @Id
    private String name;

}
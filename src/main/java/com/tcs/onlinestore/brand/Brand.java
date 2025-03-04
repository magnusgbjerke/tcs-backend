package com.tcs.onlinestore.brand;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brand {
    @Id
    private String name;
}
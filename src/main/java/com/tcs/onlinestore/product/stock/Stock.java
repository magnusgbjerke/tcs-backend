package com.tcs.onlinestore.product.stock;

import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.size.Size;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(Stock.StockPK.class)
public class Stock {
    @Id
    private Size size;

    @Id
    private Product id;

    private int quantity;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class StockPK implements Serializable {
        @ManyToOne
        private Size size;
        @ManyToOne
        private Product id;
    }
}

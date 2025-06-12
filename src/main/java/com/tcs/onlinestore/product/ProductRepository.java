package com.tcs.onlinestore.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT p.* " +
            "FROM Product p " +
            "INNER JOIN type t on t.name=p.type " +
            "WHERE (:search IS NULL OR p.name ILIKE '%' || :search || '%') " +
            "AND (:customerCategory IS NULL OR p.customer_category = :customerCategory) " +
            "AND (:productCategory IS NULL OR t.product_category = :productCategory) " +
            "AND (:type IS NULL OR p.type = :type)",
            nativeQuery = true)
    List<Product> searchProducts(@Param("search") String search,
                                 @Param("customerCategory") String customerCategory,
                                 @Param("productCategory") String productCategory,
                                 @Param("type") String type);
}

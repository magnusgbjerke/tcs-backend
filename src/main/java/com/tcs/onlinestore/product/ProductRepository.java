package com.tcs.onlinestore.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT * FROM product WHERE name ILIKE %:word%", nativeQuery = true)
    List<Product> search(@Param("word") String word);

    @Query(value = """
        SELECT * FROM product
        WHERE (:search IS NULL OR name ILIKE %:search%)
        AND (:customerCategory IS NULL OR customer_category = :customerCategory)
        AND (:productCategory IS NULL OR type IN (
            SELECT id FROM type WHERE product_category = :productCategory
        ))
        AND (:type IS NULL OR type = :type)
        AND (:minPrice IS NULL OR price >= :minPrice)
        AND (:maxPrice IS NULL OR price <= :maxPrice)
        """, nativeQuery = true)
    List<Product> searchProducts(@Param("search") String search,
                                 @Param("customerCategory") String customerCategory,
                                 @Param("productCategory") String productCategory,
                                 @Param("type") String type,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);
}

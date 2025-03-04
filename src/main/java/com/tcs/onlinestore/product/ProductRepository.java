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
}

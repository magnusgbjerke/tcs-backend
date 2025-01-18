package com.tcs.onlinestore.product.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Stock.StockPK> {

    @Query(value = "SELECT * FROM stock where id_id=:id", nativeQuery = true)
    List<Stock> findTypeById(Integer id);

}

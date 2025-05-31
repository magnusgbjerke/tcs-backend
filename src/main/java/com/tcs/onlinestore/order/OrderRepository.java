package com.tcs.onlinestore.order;

import com.tcs.onlinestore.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from order_entity where user_id=:userId and is_paid=false", nativeQuery = true)
    Optional<Order> findActiveOrderByUserId(UUID userId);
}

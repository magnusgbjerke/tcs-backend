package com.tcs.onlinestore.order.orderline;

import com.tcs.onlinestore.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    @Query(value = "select * from order_line where order_inst_id=:orderId", nativeQuery = true)
    List<OrderLine> findByOrderId(Long orderId);
}

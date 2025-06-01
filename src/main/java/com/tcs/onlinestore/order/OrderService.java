package com.tcs.onlinestore.order;

import com.tcs.onlinestore.order.orderline.OrderLine;
import com.tcs.onlinestore.order.orderline.OrderLineDTO;
import com.tcs.onlinestore.order.orderline.OrderLineRepository;
import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.size.Size;
import com.tcs.onlinestore.util.HelperService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final HelperService helperService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderLineRepository orderLineRepository,
                        HelperService helperService) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.helperService = helperService;
    }

    public OrderDTO getActiveOrder(Jwt jwt) {
        // Find order by userId
        Optional<Order> order = orderRepository.findActiveOrderByUserId(UUID.fromString(jwt.getSubject()));
        if (order.isEmpty()) {
            Order newOrder = createEmptyOrder(jwt);
            List<OrderLineDTO> emptyList = new ArrayList<>();
            return new OrderDTO(
                    newOrder.getOrderCreated(),
                    newOrder.isPaid(),
                    emptyList
            );
        }
        List<OrderLine> orderLines = orderLineRepository.findByOrderId(order.get().getId());
        List<OrderLineDTO> orderLinesDTO = new ArrayList<>();
        for (int i = 0; i < orderLines.size(); i++) {
            orderLinesDTO.add(
                    new OrderLineDTO(
                            helperService.convertToProductResponseDTO(
                                    orderLines.get(i).getProduct()
                            ),
                            orderLines.get(i).getSize().getName(),
                            orderLines.get(i).getQuantity()
                    )
            );
        }
        return new OrderDTO(
                order.get().getOrderCreated(),
                order.get().isPaid(),
                orderLinesDTO
        );
    }

    @Transactional
    public void createOrder(Jwt jwt, List<CreateOrderDTO> order) {
        // Check if user already has an active order
        helperService.checkActiveOrder(UUID.fromString(jwt.getSubject()));
        // Save order
        Order savedOrder = orderRepository.save(new Order(UUID.fromString(jwt.getSubject()), LocalDateTime.now(), false));
        for (int i = 0; i < order.size(); i++) {
            // Get product
            Product product = helperService.getProduct(order.get(i).getProductId());
            // Get size
            Size size = helperService.getSize(order.get(i).getSizeName());
            // Save orderlines
            orderLineRepository.save(
                    new OrderLine(
                            savedOrder,
                            product,
                            size,
                            order.get(i).getQuantity()
                    )
            );
        }
    }

    @Transactional
    public void updateOrder(Jwt jwt, List<CreateOrderDTO> order) {
        // Get active order
        Order activeOrder = helperService.getActiveOrder(UUID.fromString(jwt.getSubject()));
        // Get orderlines
        List<OrderLine> oldOrderLines = orderLineRepository.findByOrderId(activeOrder.getId());
        // Safe remove orderlines
        for (int i = 0; i < oldOrderLines.size(); i++) {
            orderLineRepository.delete(oldOrderLines.get(i));
        }
        // Save new orderlines
        for (int i = 0; i < order.size(); i++) {
            // Get product
            Product product = helperService.getProduct(order.get(i).getProductId());
            // Get size
            Size size = helperService.getSize(order.get(i).getSizeName());
            // Save orderlines
            orderLineRepository.save(
                    new OrderLine(
                            activeOrder,
                            product,
                            size,
                            order.get(i).getQuantity()
                    )
            );
        }
    }

    @Transactional
    public void checkoutOrder(Jwt jwt) {
        // Get active order
        Order activeOrder = helperService.getActiveOrder(UUID.fromString(jwt.getSubject()));
        // Set as paid
        activeOrder.setPaid(true);
        // Save
        orderRepository.save(activeOrder);
    }

    public Order createEmptyOrder(Jwt jwt) {
        // Check if user already has an active order
        helperService.checkActiveOrder(UUID.fromString(jwt.getSubject()));
        // Save order
        return orderRepository.save(new Order(UUID.fromString(jwt.getSubject()), LocalDateTime.now(), false));
    }
}
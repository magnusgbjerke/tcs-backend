package com.tcs.onlinestore.order;

import com.tcs.onlinestore.category.customerCategory.CustomerCategory;
import com.tcs.onlinestore.category.customerCategory.CustomerCategoryRepository;
import com.tcs.onlinestore.category.productCategory.ProductCategory;
import com.tcs.onlinestore.category.productCategory.ProductCategoryRepository;
import com.tcs.onlinestore.exception.ConflictException;
import com.tcs.onlinestore.exception.EntityNotFoundException;
import com.tcs.onlinestore.order.orderline.OrderLine;
import com.tcs.onlinestore.order.orderline.OrderLineDTO;
import com.tcs.onlinestore.order.orderline.OrderLineRepository;
import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.ProductRepository;
import com.tcs.onlinestore.product.ProductResponseDTO;
import com.tcs.onlinestore.product.ValidTypesDTO;
import com.tcs.onlinestore.product.size.Size;
import com.tcs.onlinestore.product.size.SizeRepository;
import com.tcs.onlinestore.product.stock.Stock;
import com.tcs.onlinestore.product.stock.StockRepository;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import com.tcs.onlinestore.product.type.Type;
import com.tcs.onlinestore.product.type.TypeRepository;
import com.tcs.onlinestore.util.HelperService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final CustomerCategoryRepository customerCategoryRepository;
    private final SizeRepository sizeRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final HelperService helperService;

    @Autowired
    public OrderService(ProductRepository productRepository, StockRepository stockRepository,
                        CustomerCategoryRepository customerCategoryRepository,
                        SizeRepository sizeRepository, OrderRepository orderRepository,
                        OrderLineRepository orderLineRepository,
                        HelperService helperService) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.customerCategoryRepository = customerCategoryRepository;
        this.sizeRepository = sizeRepository;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.helperService = helperService;
    }

    public OrderDTO getActiveOrder(Jwt jwt) {
        // Find order by userId
        Order order = orderRepository.findActiveOrderByUserId(UUID.fromString(jwt.getSubject())).orElseThrow(
                () -> new EntityNotFoundException("User have not created an order"));
        List<OrderLine> orderLines = orderLineRepository.findByOrderId(order.getId());
        List<OrderLineDTO> orderLinesDTO = new ArrayList<>();
        for (int i = 0; i < orderLines.size(); i++) {
            orderLinesDTO.add(
                    new OrderLineDTO(
                            helperService.convertToDTO(
                                    orderLines.get(i).getProduct()
                            ),
                            orderLines.get(i).getSize().getName(),
                            orderLines.get(i).getQuantity()
                    )
            );
        }
        return new OrderDTO(
                order.getOrderCreated(),
                order.isPaid(),
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
}
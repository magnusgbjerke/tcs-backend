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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public OrderService(ProductRepository productRepository, StockRepository stockRepository,
                        CustomerCategoryRepository customerCategoryRepository,
                        SizeRepository sizeRepository, OrderRepository orderRepository,
                        OrderLineRepository orderLineRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.customerCategoryRepository = customerCategoryRepository;
        this.sizeRepository = sizeRepository;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public OrderDTO getOrder(UUID userId) {
        // Find order by userId
        Order order = orderRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("User have not created an order"));
        List<OrderLine> orderLines = orderLineRepository.findByOrderId(order.getId());
        List<OrderLineDTO> orderLinesDTO = new ArrayList<>();
        for (int i = 0; i < orderLines.size(); i++) {
            orderLinesDTO.add(new OrderLineDTO(convertToDTO(orderLines.get(i).getProduct()), orderLines.get(i).getQuantity()));
        }
        return new OrderDTO(
                order.getOrderCreated(),
                order.isPaid(),
                orderLinesDTO
        );
    }

    @Transactional
    public void createOrder(List<CreateOrderDTO> order) {
        // Check if user already has an order
        //orderRepository.findByUserId().ifPresent(x -> {
        //    throw new ConflictException("User already has an order");
        // Save order
        Order savedOrder = orderRepository.save(new Order(UUID.fromString("6630d115-3a33-494f-9747-a4604dced5b3"), LocalDateTime.now(), false));
        List<OrderLineDTO> orderlineslist = new ArrayList<>();
        for (int i = 0; i < order.size(); i++) {
            // Get product
            Product product = productRepository.findById(order.get(i).getProductId()).orElseThrow(
                    () -> new EntityNotFoundException("Product not found"));
            // Save orderlines
            orderLineRepository.save(
                    new OrderLine(
                            savedOrder,
                            product,
                            order.get(i).getQuantity()
                    )
            );
        }
    }


    private ProductResponseDTO convertToDTO(Product products) {
        return new ProductResponseDTO(
                products.getId(),
                products.getName(),
                products.getBrand().getName(),
                products.getDescription(),
                products.getRating(),
                products.getImage(),
                stockForProduct(products),
                products.getType().getName(),
                products.getCustomerCategory().getName(),
                products.getType().getProductCategory().getName(),
                products.getPrice()
        );
    }

    public List<StockResponseDTO> stockForProduct(Product product) {
        List<Stock> stock = stockRepository.findTypeById(product.getId());
        List<StockResponseDTO> stockResponse = new ArrayList<>();
        stock.forEach(x ->
                stockResponse.add(
                        new StockResponseDTO(
                                x.getSize().getName(),
                                x.getQuantity()
                        )
                )
        );
        return stockResponse;
    }
}
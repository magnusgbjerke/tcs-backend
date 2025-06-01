package com.tcs.onlinestore.util;

import com.tcs.onlinestore.exception.ConflictException;
import com.tcs.onlinestore.exception.EntityNotFoundException;
import com.tcs.onlinestore.order.Order;
import com.tcs.onlinestore.order.OrderRepository;
import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.ProductRepository;
import com.tcs.onlinestore.product.ProductResponseDTO;
import com.tcs.onlinestore.product.size.Size;
import com.tcs.onlinestore.product.size.SizeRepository;
import com.tcs.onlinestore.product.stock.Stock;
import com.tcs.onlinestore.product.stock.StockRepository;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class HelperService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final SizeRepository sizeRepository;

    @Autowired
    public HelperService(ProductRepository productRepository, StockRepository stockRepository,
                         OrderRepository orderRepository,
                         SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.orderRepository = orderRepository;
        this.sizeRepository = sizeRepository;
    }

    public Order getActiveOrder(UUID userId) {
        return orderRepository.findActiveOrderByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("User does not have an active order."));
    }

    public void checkActiveOrder(UUID userId) {
        orderRepository.findActiveOrderByUserId(userId).ifPresent(x -> {
            throw new ConflictException("User already has an active order");
        });
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));
    }

    public Size getSize(String sizeName) {
        return sizeRepository.findById(sizeName).orElseThrow(
                () -> new EntityNotFoundException("Size not found"));
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

    public ProductResponseDTO convertToProductResponseDTO(Product products) {
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
}
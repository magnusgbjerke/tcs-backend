package com.tcs.onlinestore.product;

import com.tcs.onlinestore.globalExceptionHandler.EntityNotFoundException;
import com.tcs.onlinestore.product.stock.Stock;
import com.tcs.onlinestore.product.stock.StockRepository;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        // Find all products
        List<Product> products = productRepository.findAll();
        // Find stock for each product
        for (int i = 0; i < products.size(); i++) {
            productResponse.add(
                    new ProductResponseDTO(
                            products.get(i).getId(),
                            products.get(i).getBrand().getName(),
                            products.get(i).getName(),
                            stockForProduct(products.get(i)),
                            products.get(i).getType().getName(),
                            products.get(i).getCustomerCategory().getName(),
                            products.get(i).getType().getProductCategory().getName()
                    )
            );
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " was not found"));
        return new ResponseEntity<>(product, HttpStatus.OK);
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
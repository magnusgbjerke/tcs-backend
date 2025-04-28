package com.tcs.onlinestore.product;

import com.tcs.onlinestore.globalExceptionHandler.EntityNotFoundException;
import com.tcs.onlinestore.product.customerCategory.CustomerCategory;
import com.tcs.onlinestore.product.customerCategory.CustomerCategoryRepository;
import com.tcs.onlinestore.product.productCategory.ProductCategory;
import com.tcs.onlinestore.product.productCategory.ProductCategoryRepository;
import com.tcs.onlinestore.product.stock.Stock;
import com.tcs.onlinestore.product.stock.StockRepository;
import com.tcs.onlinestore.product.stock.StockResponseDTO;
import com.tcs.onlinestore.product.type.Type;
import com.tcs.onlinestore.product.type.TypeRepository;
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
    private final CustomerCategoryRepository customerCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, StockRepository stockRepository,
                          CustomerCategoryRepository customerCategoryRepository,
                          ProductCategoryRepository productCategoryRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.customerCategoryRepository = customerCategoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.typeRepository = typeRepository;
    }

    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        // Find all products
        List<Product> products = productRepository.findAll();
        // Find stock for each product
        for (int i = 0; i < products.size(); i++) {
            productResponse.add(
                    convertToDTO(products.get(i))
            );
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDTO> getProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " was not found"));
        return new ResponseEntity<>(convertToDTO(product), HttpStatus.OK);
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

    public ResponseEntity<List<ProductResponseDTO>> getFilteredProducts(String search, String customerCategory, String productCategory, String type) {
        List<Product> products = productRepository.searchProducts(search, customerCategory, productCategory, type);
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productResponse.add(
                    convertToDTO(products.get(i))
            );
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
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

    public ResponseEntity<ValidTypesDTO> getValidTypes() {
        List<String> listValidCustomerCategory = new ArrayList<>();
        List<String> listValidProductCategory = new ArrayList<>();
        List<String> listValidType = new ArrayList<>();

        List<CustomerCategory> allValidCustomerCategory = customerCategoryRepository.findAll();
        List<ProductCategory> allValidProductCategory = productCategoryRepository.findAll();
        List<Type> allValidType = typeRepository.findAll();

        allValidCustomerCategory.forEach(x-> listValidCustomerCategory.add(x.getName()));
        allValidProductCategory.forEach(x-> listValidProductCategory.add(x.getName()));
        allValidType.forEach(x-> listValidType.add(x.getName()));

        ValidTypesDTO validTypesDTO = new ValidTypesDTO(listValidCustomerCategory, listValidProductCategory, listValidType);
        return new ResponseEntity<>(validTypesDTO, HttpStatus.OK);
    }
}
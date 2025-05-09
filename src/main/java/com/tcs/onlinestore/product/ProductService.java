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
import com.tcs.onlinestore.product.ValidTypesDTO;
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
    public ProductService(ProductRepository productRepository,
                          StockRepository stockRepository,
                          CustomerCategoryRepository customerCategoryRepository,
                          ProductCategoryRepository productCategoryRepository,
                          TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.customerCategoryRepository = customerCategoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.typeRepository = typeRepository;
    }

    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            productResponse.add(buildProductDTO(product));
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDTO> getProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " was not found"));
        ProductResponseDTO dto = buildProductDTO(product);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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

    public ResponseEntity<List<ProductResponseDTO>> getAutocomplete(String searchWord) {
        List<Product> products = productRepository.search(searchWord);
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        for (Product product : products) {
            productResponse.add(buildProductDTO(product));
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDTO>> getFilteredProducts(String search, String customerCategory, String productCategory, String type) {
        List<Product> products = productRepository.searchProducts(search, customerCategory, productCategory, type, null, null);
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        for (Product product : products) {
            productResponse.add(buildProductDTO(product));
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<ValidTypesDTO> getValidTypes() {
        List<String> customerCategories = customerCategoryRepository.findAll()
                .stream().map(CustomerCategory::getName).toList();

        List<String> productCategories = productCategoryRepository.findAll()
                .stream().map(ProductCategory::getName).toList();

        List<String> types = typeRepository.findAll()
                .stream().map(Type::getName).toList();

        ValidTypesDTO dto = new ValidTypesDTO(customerCategories, productCategories, types);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public List<Product> searchProducts(String search, String customerCategory, String productCategory, String type, Double minPrice, Double maxPrice) {
        return productRepository.searchProducts(search, customerCategory, productCategory, type, minPrice, maxPrice);
    }

    private ProductResponseDTO buildProductDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand().getName(),
                product.getDescription(),
                product.getRating(),
                product.getImage(),
                stockForProduct(product),
                product.getType().getName(),
                product.getCustomerCategory().getName(),
                product.getType().getProductCategory().getName(),
                product.getPrice(),
                product.getPriceAfterDiscount()
        );
    }
}

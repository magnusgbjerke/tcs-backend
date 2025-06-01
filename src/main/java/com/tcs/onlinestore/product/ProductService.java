package com.tcs.onlinestore.product;

import com.tcs.onlinestore.category.customerCategory.CustomerCategoryRepository;
import com.tcs.onlinestore.category.productCategory.ProductCategoryRepository;
import com.tcs.onlinestore.product.type.TypeRepository;
import com.tcs.onlinestore.util.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerCategoryRepository customerCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final TypeRepository typeRepository;
    private final HelperService helperService;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CustomerCategoryRepository customerCategoryRepository,
                          ProductCategoryRepository productCategoryRepository, TypeRepository typeRepository,
                          HelperService helperService) {
        this.productRepository = productRepository;
        this.customerCategoryRepository = customerCategoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.typeRepository = typeRepository;
        this.helperService = helperService;
    }

    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        // Find all products
        List<Product> products = productRepository.findAll();
        // Find stock for each product
        for (int i = 0; i < products.size(); i++) {
            productResponse.add(
                    helperService.convertToProductResponseDTO(products.get(i))
            );
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDTO> getProduct(String id) {
        Product product = helperService.getProduct(id);
        return new ResponseEntity<>(helperService.convertToProductResponseDTO(product), HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDTO>> getFilteredProducts(String search, String customerCategory, String productCategory, String type) {
        List<Product> products = productRepository.searchProducts(search, customerCategory, productCategory, type);
        List<ProductResponseDTO> productResponse = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productResponse.add(
                    helperService.convertToProductResponseDTO(products.get(i))
            );
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    public ResponseEntity<ValidTypesDTO> getValidTypes() {
        List<String> listValidCustomerCategory = new ArrayList<>();
        List<String> listValidProductCategory = new ArrayList<>();
        List<String> listValidType = new ArrayList<>();

        customerCategoryRepository.findAll().forEach(x-> listValidCustomerCategory.add(x.getName()));
        productCategoryRepository.findAll().forEach(x-> listValidProductCategory.add(x.getName()));
        typeRepository.findAll().forEach(x-> listValidType.add(x.getName()));

        ValidTypesDTO validTypesDTO = new ValidTypesDTO(listValidCustomerCategory, listValidProductCategory, listValidType);
        return new ResponseEntity<>(validTypesDTO, HttpStatus.OK);
    }
}
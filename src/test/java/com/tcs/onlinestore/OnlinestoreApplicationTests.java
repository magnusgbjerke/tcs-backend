package com.tcs.onlinestore;

import com.tcs.onlinestore.brand.Brand;
import com.tcs.onlinestore.product.Product;
import com.tcs.onlinestore.product.customerCategory.CustomerCategory;
import com.tcs.onlinestore.product.productCategory.ProductCategory;
import com.tcs.onlinestore.product.type.Type;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OnlinestoreApplicationTests {

    @Test
    public void testIdName() throws Exception {
        /*
        Product product1 = new Product(new Brand("Velora"), "Houndstooth Blazer i Regular Fit",
                new CustomerCategory("men"), new Type("hoodies", new ProductCategory()));
        System.out.println(product1);

        Product product2 = new Product(new Brand("Drift & Dune"), "Oversized Hoodie i Kangaroo Pocket",
                new CustomerCategory("men"), new Type("hoodies", new ProductCategory()));
        System.out.println(product2);

        Product product3 = new Product(new Brand("Noxen"), "Cargo Joggers i Elastic Waist",
                new CustomerCategory("men"), new Type("hoodies", new ProductCategory()));
        System.out.println(product3);

        Product product4 = new Product(new Brand("Urban Loom"), "Graphic Tee i Vintage Wash",
                new CustomerCategory("men"), new Type("hoodies", new ProductCategory()));
        System.out.println(product4);

        Product product5 = new Product(new Brand("LuxeRoots"), "Basic Hoodie i Warm Fleece",
                new CustomerCategory("men"), new Type("hoodies", new ProductCategory()));
        System.out.println(product5);
        */
    }
}

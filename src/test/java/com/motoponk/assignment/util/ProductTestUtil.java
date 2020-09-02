package com.motoponk.assignment.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.User;

import com.motoponk.assignment.model.dto.OrderDTO;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.model.entity.Order;
import com.motoponk.assignment.model.entity.Product;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTestUtil {

    public static final String SAMPLE_PRODUCT_1_SKU = "PR-01-V1";
    public static final String SAMPLE_PRODUCT_2_SKU = "PR-02-V1";
    private static final BigDecimal SAMPLE_PRICE = BigDecimal.valueOf(50.00);
    public static final String SAMPLE_EMAIL = "dogukandogan@dogukandogan.com";
    
    private static final String SAMPLE_PASSWORD = "secret";

    public static ProductDTO createSampleProductDTO() {
        return ProductDTO.builder()
                .sku(SAMPLE_PRODUCT_1_SKU)
                .name("Product 1")
                .price(SAMPLE_PRICE)
                .build();
    }
    
    public static ProductDTO createSampleProduct2DTO() {
        return ProductDTO.builder()
                .sku(SAMPLE_PRODUCT_2_SKU)
                .name("Product 2")
                .price(SAMPLE_PRICE)
                .build();
    }
    
    public static Product createSampleProduct() {
        return new Product(SAMPLE_PRODUCT_1_SKU, "Product 1", SAMPLE_PRICE);
    }
    
    public static OrderDTO createSampleOrderDTO() {
        OrderDTO orderDTO = OrderDTO.builder()
                       .build();
        orderDTO.setEmail(SAMPLE_EMAIL);
        orderDTO.setProducts(Arrays.asList(createSampleProductDTO()));
        return orderDTO;
    }
    
    public static Order createSampleOrder() {
        Order order = Order.builder()
                    .email(SAMPLE_EMAIL)
                    .totalPrice(SAMPLE_PRICE)
                    .build();
        order.setCreatedTime(LocalDateTime.now());
        order.setProducts(createSampleProducts());
        return order;
    }
    
    private static Set<Product> createSampleProducts() {
        Set<Product> products = new HashSet<>();
        products.add(createSampleProduct());
        return products;
    }
    
    public static User createSampleAuthenticationUser() {
        return new User(SAMPLE_EMAIL, SAMPLE_PASSWORD, Collections.emptyList());
    }
    
}

package com.motoponk.assignment.util;

import java.math.BigDecimal;

import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.model.entity.Product;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTestUtil {

    public static final String SAMPLE_PRODUCT_1_SKU = "PR-01-V1";

    public static ProductDTO createSampleProductDTO() {
        return ProductDTO.builder()
                .sku(SAMPLE_PRODUCT_1_SKU)
                .name("Product 1")
                .price(BigDecimal.valueOf(50.00))
                .build();
    }
    
    public static Product createSampleProduct() {
        return new Product(SAMPLE_PRODUCT_1_SKU, "Product 1", BigDecimal.valueOf(50.00));
    }
    
}

package com.motoponk.assignment.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.motoponk.assignment.model.entity.Product;
import com.motoponk.assignment.util.ProductTestUtil;

@DataJpaTest
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void shouldRetreiveProductsAfterAdditionProperly() {
        
        assertTrue(productRepository.findAll().isEmpty());
        
        Product product = ProductTestUtil.createSampleProduct();
        productRepository.save(product);
        
        assertFalse(productRepository.findAll().isEmpty());
        assertTrue(productRepository.findAll().size() == 1);
        assertTrue(productRepository.findBySku(ProductTestUtil.SAMPLE_PRODUCT_1_SKU).isPresent());
    }
    
}

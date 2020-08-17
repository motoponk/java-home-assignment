package com.motoponk.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.service.ProductService;
import com.motoponk.assignment.util.ProductTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
    
    private static final String BASE_URL = "/api/products";
    
    private String url;
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private ProductService productService;
    
    
    @BeforeEach
    public void initTest() {
        this.url = "http://localhost:" + port + BASE_URL;
    }
    
    @Test
    public void testReadProductsForInitialSetup() {
        int size = readProductsSize();
        assertTrue(size == 0);
    }
    
    @Test
    public void testReadProducts() {
        
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        productService.addProduct(productDTO);
        
        int size = readProductsSize();
        assertTrue(size == 1);
    }
    
    private int readProductsSize() {
        return this.restTemplate.getForObject(url, List.class).size();
    }
    
    @Test
    @Disabled
    public void testAddProduct() {
        int beforeSize = readProductsSize();
        ProductDTO sampleProductDTO = ProductTestUtil.createSampleProductDTO();
        
        ResponseEntity<Void> responseEntity = 
                restTemplate.postForEntity(url, sampleProductDTO, Void.class);
        
        assertEquals(201, responseEntity.getStatusCodeValue());
        
        int sizeAfterAddition = readProductsSize();
        assertTrue(sizeAfterAddition == beforeSize + 1);
    }
    
}

package com.motoponk.assignment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.motoponk.assignment.model.dto.OrderRequestDTO;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.service.ProductService;
import com.motoponk.assignment.util.AbstractIntegrationTest;
import com.motoponk.assignment.util.ProductTestUtil;

public class OrderControllerIT extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/orders";
    
    @Autowired
    private ProductService productService;
    
    
    @Test
    public void shouldSaveOrderProperly() throws Exception {
        ProductDTO sampleProductDTO = ProductTestUtil.createSampleProductDTO();
        productService.addProduct(sampleProductDTO);
        
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setEmail("mail@mail.com");
        requestDTO.setProducts(Arrays.asList(sampleProductDTO));
        
        String json = this.objectMapper.writeValueAsString(requestDTO);
        
        this.mockMvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                ).andExpect(status().isCreated());
    }
    
}

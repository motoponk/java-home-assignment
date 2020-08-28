package com.motoponk.assignment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.util.AbstractIntegrationTest;
import com.motoponk.assignment.util.ProductTestUtil;

public class ProductControllerIT extends AbstractIntegrationTest {
    
    private static final String BASE_URL = "/api/products";
    
    @Test
    public void shouldReadProductsProperly() throws Exception {
        this.mockMvc.perform(get(BASE_URL))
                    .andExpect(status().isOk());
    }
    
    
    @Test
    public void shouldAddSampleProductProperly() throws Exception {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        String json = this.objectMapper.writeValueAsString(productDTO);
        
        this.mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                    .andExpect(status().isCreated());
    }
    
}

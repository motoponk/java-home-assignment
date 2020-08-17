package com.motoponk.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.motoponk.assignment.exception.NoSuchProductException;
import com.motoponk.assignment.exception.ProductAlreadyExistException;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.service.ProductService;
import com.motoponk.assignment.util.ProductTestUtil;

public class ProductControllerTest {

    private ProductService productService = mock(ProductService.class);
    
    private ProductController productController = new ProductController(productService);
    
    @Test
    public void shouldListProductsProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        given(productService.readProducts()).willReturn(Arrays.asList(productDTO));
        
        List<ProductDTO> products = productService.readProducts();
        assertTrue(products.size() == 1);
    }
    
    @Test
    public void shouldAddProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        productController.addProduct(productDTO);
    }
    
    
    @Test
    public void shouldThrowProductAlreadyExistExeptionForAdditionWithTheSameSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        
        doThrow(new ProductAlreadyExistException(ProductTestUtil.SAMPLE_PRODUCT_1_SKU))
                .when(productService).addProduct(productDTO);
        
        ProductAlreadyExistException exception = assertThrows(ProductAlreadyExistException.class, 
                () -> productController.addProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(productDTO.getSku()));
    }
    
    @Test
    public void shouldUpdateProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        productController.updateProduct(productDTO);
    }
    
    
    @Test
    public void shouldThrowNoSuchProductExeptionForUpdateWithNonExistingSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        
        doThrow(new NoSuchProductException(ProductTestUtil.SAMPLE_PRODUCT_1_SKU))
                .when(productService).updateProduct(productDTO);
        
        NoSuchProductException exception = assertThrows(NoSuchProductException.class, 
                () -> productController.updateProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(productDTO.getSku()));
    }
    
    @Test
    public void shouldDeleteProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        productController.deleteProduct(productDTO);
    }
    
    
    @Test
    public void shouldThrowNoSuchProductExeptionForDeletionWithNonExistingSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        
        doThrow(new NoSuchProductException(ProductTestUtil.SAMPLE_PRODUCT_1_SKU))
                .when(productService).deleteProduct(productDTO);
        
        NoSuchProductException exception = assertThrows(NoSuchProductException.class, 
                () -> productController.deleteProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(productDTO.getSku()));
    }
    
}

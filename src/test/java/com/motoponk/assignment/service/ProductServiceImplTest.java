package com.motoponk.assignment.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.motoponk.assignment.exception.NoSuchProductException;
import com.motoponk.assignment.exception.ProductAlreadyExistException;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.model.entity.Product;
import com.motoponk.assignment.repository.ProductRepository;
import com.motoponk.assignment.util.ProductTestUtil;

public class ProductServiceImplTest {

    private ProductRepository productRepository = mock(ProductRepository.class);
    private ProductServiceImpl productService = new ProductServiceImpl(productRepository);
    
    @Test
    public void shouldListProductsProperly() {
        Product product = ProductTestUtil.createSampleProduct();
        given(productRepository.findAll()).willReturn(Arrays.asList(product));
        
        List<ProductDTO> products = productService.readProducts();
        assertTrue(products.size() == 1);
    }
    
    @Test
    public void shouldAddProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        productService.addProduct(productDTO);
    }
    
    
    @Test
    public void shouldThrowProductAlreadyExistExeptionForAdditionWithTheSameSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        Product product = ProductTestUtil.createSampleProduct();
        
        when(productRepository.findBySku(productDTO.getSku())).thenReturn(Optional.of(product));
        
        ProductAlreadyExistException exception = assertThrows(ProductAlreadyExistException.class, 
                () -> productService.addProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(product.getSku()));
    }
    
    
    @Test
    public void shouldUpdateProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        
        Product product = ProductTestUtil.createSampleProduct();
        given(productRepository.findBySku(productDTO.getSku())).willReturn(Optional.of(product));
        given(productRepository.save(product)).willReturn(product);
        
        productService.updateProduct(productDTO);
    }
    
    
    @Test
    public void shouldThrowNoSuchProductExeptionForUpdateWithNonExistingSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        given(productRepository.findBySku(productDTO.getSku())).willReturn(Optional.empty());
        
        NoSuchProductException exception = assertThrows(NoSuchProductException.class, 
                () -> productService.updateProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(productDTO.getSku()));
    }
    
    @Test
    public void shouldDeleteProductProperly() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        
        Product product = ProductTestUtil.createSampleProduct();
        given(productRepository.findBySku(productDTO.getSku())).willReturn(Optional.of(product));
        
        productService.deleteProduct(productDTO);
    }
    
    @Test
    public void shouldThrowNoSuchProductExeptionForDeletionWithNonExistingSKU() {
        ProductDTO productDTO = ProductTestUtil.createSampleProductDTO();
        given(productRepository.findBySku(productDTO.getSku())).willReturn(Optional.empty());
        
        NoSuchProductException exception = assertThrows(NoSuchProductException.class, 
                () -> productService.deleteProduct(productDTO));
        
        assertTrue(exception.getMessage().contains(productDTO.getSku()));
    }
    
}

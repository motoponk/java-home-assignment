package com.motoponk.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public List<ProductDTO> readProducts() {
        List<ProductDTO> products = productService.readProducts();
        log.debug("Returning read products result.");
        return products;
    }
    
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct(@Valid ProductDTO productDTO) {
        productService.addProduct(productDTO);
        log.info("Add product operation has been executed successfully for product DTO: {}", productDTO);
    }
    
    @PutMapping
    public ProductDTO updateProduct(@Valid ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productDTO);
        log.info("Update product operation has been executed successfully for product DTO: {}", updatedProduct);
        return updatedProduct;
    }
    
    @DeleteMapping
    public void deleteProduct(ProductDTO productDTO) {
        productService.deleteProduct(productDTO);
        log.info("Delete product operation has been executed successfully for product DTO: {}", productDTO);
    }
}

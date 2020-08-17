package com.motoponk.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motoponk.assignment.exception.NoSuchProductException;
import com.motoponk.assignment.exception.ProductAlreadyExistException;
import com.motoponk.assignment.model.dto.ProductDTO;
import com.motoponk.assignment.model.entity.Product;
import com.motoponk.assignment.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_CACHE_NAME = "products";
    
    private final ProductRepository productRepositoy;

    public ProductServiceImpl(ProductRepository productRepositoy) {
        this.productRepositoy = productRepositoy;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = PRODUCTS_CACHE_NAME)
    public List<ProductDTO> readProducts() {
        return productRepositoy.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_NAME, allEntries = true)
    public void addProduct(final ProductDTO productDTO) {
        verifyProductForAddOperation(productDTO);
        final Product product = 
                new Product(productDTO.getSku(), productDTO.getName(), productDTO.getPrice());
        final Product savedProduct = productRepositoy.save(product);
        log.debug("Product is saved successfully. Saved product: {}", savedProduct);
    }

    private void verifyProductForAddOperation(final ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepositoy.findBySku(productDTO.getSku());
        if (existingProduct.isPresent()) {
            log.warn("Product with SKU: {} is already exist.", productDTO.getSku());
            throw new ProductAlreadyExistException(productDTO.getSku());
        }
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_NAME, allEntries = true)
    public ProductDTO updateProduct(final ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepositoy.findBySku(productDTO.getSku());
        if (existingProduct.isEmpty()) {
            log.warn("Product with SKU: {} does not exist.", productDTO.getSku());
            throw new NoSuchProductException(productDTO.getSku());
        }
        final Product product = existingProduct.get();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        final Product result = productRepositoy.save(product);
        log.debug("Update product operation is handled successfully. Saved product is {}.", result);
        return new ProductDTO(result);
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_NAME, allEntries = true)
    public void deleteProduct(final ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepositoy.findBySku(productDTO.getSku());
        if (existingProduct.isEmpty()) {
            log.warn("Product with SKU: {} does not exist.", productDTO.getSku());
            throw new NoSuchProductException(productDTO.getSku());
        }
        final Product product = existingProduct.get();
        productRepositoy.delete(product);
        log.debug("Delete product operation is handled successfully. Deleted product is {}.", product);
    }

}

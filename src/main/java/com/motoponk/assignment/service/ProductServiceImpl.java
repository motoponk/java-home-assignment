package com.motoponk.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_CACHE_NAME = "products";
    
    private final ProductRepository productRepositoy;

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
        Product product = productRepositoy.findBySku(productDTO.getSku()).orElseThrow(
                () -> new NoSuchProductException(productDTO.getSku()));
        return applyUpdate(productDTO, product);
    }

    private ProductDTO applyUpdate(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return new ProductDTO(productRepositoy.save(product));
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_NAME, allEntries = true)
    public void deleteProduct(final ProductDTO productDTO) {
        Product product = productRepositoy.findBySku(productDTO.getSku()).orElseThrow(
                () -> new NoSuchProductException(productDTO.getSku()));
        productRepositoy.delete(product);
        log.debug("Delete product operation is handled successfully. Deleted product is {}.", product);
    }

}

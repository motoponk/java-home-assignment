package com.motoponk.assignment.service;

import java.util.List;

import com.motoponk.assignment.model.dto.ProductDTO;

public interface ProductService {

    List<ProductDTO> readProducts();

    void addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProduct(ProductDTO productDTO);

}

package com.motoponk.assignment.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.motoponk.assignment.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    
    @NotBlank(message = "SKU information of product can not be blank")
    private String sku;
    
    @NotBlank(message = "Name information of product can not be blank")
    private String name;
    
    @NotNull(message = "Price information of product can not be null")
    private BigDecimal price;
    
    public ProductDTO(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();
    }
    
}

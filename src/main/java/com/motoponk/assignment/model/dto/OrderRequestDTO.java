package com.motoponk.assignment.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private List<ProductDTO> products = new ArrayList<>(0);
    
}

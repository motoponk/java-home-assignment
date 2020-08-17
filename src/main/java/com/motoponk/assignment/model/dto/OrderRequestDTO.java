package com.motoponk.assignment.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank(message = "Email information of product can not be blank")
    @Email(message = "Email format should be correct")
    private String email;
    
    private List<ProductDTO> products = new ArrayList<>(0);
    
}

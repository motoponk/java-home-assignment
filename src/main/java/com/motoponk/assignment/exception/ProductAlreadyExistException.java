package com.motoponk.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 9008943372692254871L;
    
    private static final String MESSAGE = "Product is already exist with given SKU: ";
    
    public ProductAlreadyExistException(String sku) {
        super(MESSAGE + sku);
    }
    
    
}

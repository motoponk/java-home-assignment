package com.motoponk.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchProductException extends RuntimeException {

    private static final long serialVersionUID = 6037350057738389725L;
    
    private static final String MESSAGE = "No such product with given SKU: ";
    
    public NoSuchProductException(String sku) {
        super(MESSAGE + sku);
    }

}

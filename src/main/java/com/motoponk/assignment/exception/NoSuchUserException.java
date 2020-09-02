package com.motoponk.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchUserException extends RuntimeException {

    private static final long serialVersionUID = -7825080540251886553L;
    
    private static final String MESSAGE = "User can not be found with username: ";
    
    public NoSuchUserException(String username) {
        super(MESSAGE + username);
    }
    
}

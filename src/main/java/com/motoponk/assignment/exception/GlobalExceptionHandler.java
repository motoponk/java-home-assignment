package com.motoponk.assignment.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<?> noSuchProductException(NoSuchProductException ex, WebRequest request) {
        log.error("No such product exception is caught for request: {}", request, ex);
        ErrorDetail errorDetail = 
                new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<?> noSuchEntityException(ProductAlreadyExistException ex, WebRequest request) {
        log.error("Product already exist exception is caught for request: {}", request, ex);
        ErrorDetail errorDetail = 
                new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.error("Access to resource is denied for request: {}", request, ex);
        ErrorDetail errorDetail = 
                new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Exception is caught for request: {}", request, ex);
        ErrorDetail errorDetails = 
                new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}

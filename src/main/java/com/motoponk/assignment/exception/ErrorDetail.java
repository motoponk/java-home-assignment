package com.motoponk.assignment.exception;

import java.util.Date;

import lombok.Data;

/**
 * Error detail class will keep details of exceptions.
 * 
 * @author dogukan
 *
 */
@Data
public class ErrorDetail {
    private final Date timestamp;
    private final String message;
    private final String details;

    //TODO Randon UUID can be generated??

}

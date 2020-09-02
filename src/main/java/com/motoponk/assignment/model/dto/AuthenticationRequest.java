package com.motoponk.assignment.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull(message = "Username can not be null")
    @Email
    private String username;
    
    @NotNull(message = "Password can not be null")
    private String password;
    
}

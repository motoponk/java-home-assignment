package com.motoponk.assignment.model.dto;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class UserDTO {
    
    private final String email;
    
    private final String password;
    
    private Set<String> roles = new HashSet<>();
    
    public UserDTO(String email, String password, String... roles) {
        this.email = email;
        this.password = password;
        this.roles = Stream.of(roles).collect(toSet());
    }

}

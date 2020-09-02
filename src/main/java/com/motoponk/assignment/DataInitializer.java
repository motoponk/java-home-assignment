package com.motoponk.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.motoponk.assignment.model.dto.UserDTO;
import com.motoponk.assignment.model.entity.Role;
import com.motoponk.assignment.repository.RoleRepository;
import com.motoponk.assignment.service.auth.UserService;
import com.motoponk.assignment.util.RoleConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class DataInitializer implements CommandLineRunner {
    
    private static final String ADMIN_USER = "admin@mail.com";
    private static final String USER = "user@mail.com";
    
    private static final String DEFAULT_PASSWORD = "secret";
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        
        if (dbIsInitialized()) {
            log.info("DB is already initialized with sample user and role data.");
            return;
        }
        
        log.info("Initializing DB with sample roles and users.");
        addDefaultRoles();
        
        addSampleUsers();
        log.info("DB is initialized with sample roles and users.");
    }

    
    private boolean dbIsInitialized() {
        return !roleRepository.findAll().isEmpty();
    }


    private void addSampleUsers() {
        userService.addUser(new UserDTO(ADMIN_USER, DEFAULT_PASSWORD, RoleConstants.ADMIN));
        userService.addUser(new UserDTO(USER, DEFAULT_PASSWORD, RoleConstants.USER));
    }


    private void addDefaultRoles() {
        roleRepository.save(new Role(RoleConstants.ADMIN));
        roleRepository.save(new Role(RoleConstants.USER));
    }

}

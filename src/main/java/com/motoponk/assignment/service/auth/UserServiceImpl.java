package com.motoponk.assignment.service.auth;

import static java.util.stream.Collectors.toSet;

import java.util.Objects;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motoponk.assignment.model.dto.UserDTO;
import com.motoponk.assignment.model.entity.Role;
import com.motoponk.assignment.model.entity.User;
import com.motoponk.assignment.repository.RoleRepository;
import com.motoponk.assignment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void addUser(UserDTO userDTO) {
        User user = createUser(userDTO);
        userRepository.save(user);
    }

    private User createUser(UserDTO userDTO) {
        User user = new User();
        
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        user.setRoles(readRoles(userDTO.getRoles()));
        return user;
    }

    private Set<Role> readRoles(Set<String> roles) {
        return roles.stream().map(this::readRoleByName)
                             .filter(Objects::nonNull)
                             .collect(toSet());
    }
    
    
    private Role readRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

}

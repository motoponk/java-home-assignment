package com.motoponk.assignment.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.motoponk.assignment.model.entity.Role;
import com.motoponk.assignment.model.entity.User;

public class UserInfo extends org.springframework.security.core.userdetails.User {
    
    private static final long serialVersionUID = -1241377056795082844L;

    public UserInfo(User user) {
        super(user.getEmail(), user.getPassword(), createAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> createAuthorities(Set<Role> roles) {
        return AuthorityUtils.createAuthorityList(
                roles.stream().map(Role::getName).toArray(String[]::new));
    }

}

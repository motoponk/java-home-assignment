package com.motoponk.assignment.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Inherited
@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface RequiresAdminPrivilege {
    
}


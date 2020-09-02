package com.motoponk.assignment.security;

import lombok.experimental.UtilityClass;

@UtilityClass
class SecurityConstants {

    // TODO Move these two properties file to make configurable.
    public static final String SECRET = "VeryStrongKey";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    
    
    public static final String TOKEN_HEADER = "X-Token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
}

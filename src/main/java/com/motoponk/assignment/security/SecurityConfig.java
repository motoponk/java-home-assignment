package com.motoponk.assignment.security;

import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    
    JWTAuthenticationFilter authFilter() throws Exception {
        return new JWTAuthenticationFilter(authenticationManager());
    }
    
    JWTAuthorizationFilter autzFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager(), userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable();
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/h2-console/**", 
                             "/login", 
                             "/v2/api-docs", 
                             "/configuration/ui",
                             "/swagger-resources/**", 
                             "/configuration/security", 
                             "/swagger-ui.html", 
                             "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products").permitAll()
            .anyRequest().authenticated().and()
            /*
            .formLogin()
                .defaultSuccessUrl("/api/products")
                .failureUrl("/login?error").and() */
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        
        http.addFilter(authFilter())
            .addFilter(autzFilter());
        
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        Stream.of(HttpMethod.values())
              .forEach(m -> corsConfiguration.addAllowedMethod(m.name()));

        UrlBasedCorsConfigurationSource source = 
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

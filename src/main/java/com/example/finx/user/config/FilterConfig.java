package com.example.finx.user.config;

import com.example.finx.user.jwt.JwtFilter;
import com.example.finx.user.jwt.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtParser jwtParser;

    @Override
    public void init(HttpSecurity http) {
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class);
    }
}
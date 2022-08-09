package com.example.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(name);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, authentication.getCredentials());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

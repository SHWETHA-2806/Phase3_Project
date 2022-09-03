package com.example.demo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomAuthentication implements AuthenticationProvider {
    List<User> dummyUsers = new ArrayList<>();

    public CustomAuthentication() {
        dummyUsers.add(new User("Shwetha", "abc@123", "ROLE_USER"));
        dummyUsers.add(new User("Shwetha.admin", "123@abc", "ROLE_ADMIN"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        //jdk 8 -- stream
        Optional<User> authenticatedUser = dummyUsers.stream().filter(
                user -> user.getName().equals(name) && user.getPassword().equals(password)
        ).findFirst();

        if(!authenticatedUser.isPresent()){
            throw new BadCredentialsException("Some Text");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authenticatedUser.get().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
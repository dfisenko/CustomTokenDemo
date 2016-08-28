package com.df.configuration.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


/**
 * Created by denis on 8/27/2016.
 */
public class CustomUserService implements UserDetailsService {


    private PasswordEncoder encoder;


    public CustomUserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        CustomUser user = new CustomUser();
        user.setFirstName("Denis");
        user.setLastName("Fisenko");


        user.setPassword("password");
        user.setUsername("user");

        user.setAuthorities(Arrays.asList(new Role("USER")));


        return user;
    }
}

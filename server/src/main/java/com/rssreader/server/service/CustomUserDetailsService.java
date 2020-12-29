package com.rssreader.server.service;


import com.rssreader.server.model.User;
import com.rssreader.server.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserService userService;
    PasswordEncoder encoder;

    @Autowired
    CustomUserDetailsService(UserService userService){
        this.userService=userService;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userService.getByUsername(username);
        if(username==null) throw new UsernameNotFoundException("User not found");

        org.springframework.security.core.userdetails.User userDetails=
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        convertAuthorities(user.getRoles())
                );
        System.out.println(userDetails.toString());
        return userDetails;
    }

    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles){

        Set<GrantedAuthority> authorities=new HashSet<>();
        for (UserRole userRole : userRoles){
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return authorities;
    }
}

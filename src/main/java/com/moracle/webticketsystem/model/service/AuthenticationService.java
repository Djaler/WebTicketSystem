package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by dmitry on 8/7/2016.
 */
@Service
public class AuthenticationService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByLogin(s);
        if (user == null) {
            return null;
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.passwordAsString(), Collections.singletonList(authority));
    }
}

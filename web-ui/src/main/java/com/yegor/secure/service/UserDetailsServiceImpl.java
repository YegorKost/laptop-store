package com.yegor.secure.service;

import com.yegor.entity.UserEntity;
import com.yegor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by YegorKost on 24.03.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService<UserEntity> userService;

    @Autowired
    public UserDetailsServiceImpl(UserService<UserEntity> userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userService.getUser(s);
        User resultUser;
        Set<GrantedAuthority> roles = new HashSet<>();
        if (user == null){
            roles.add(new SimpleGrantedAuthority("anonymous"));
            resultUser = new User("Anonymous", "", roles);
        } else {
            roles.add(new SimpleGrantedAuthority(user.getRole().getRole()));
            resultUser = new User(user.getLogin(), user.getPassword(), roles);
        }
        return resultUser;
    }
}

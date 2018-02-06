package com.trading.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Objects;


public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    /*@Autowired
    private UserService userService;*/

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       /* User user = userService.getUserByEmail(email);
        if(Objects.nonNull(user)){
            List<String> authorities = user.getRoles();
            UserDetails mongoUserDetails = new UserDetails(user.getEmail(),user.getPassword(), authorities.toArray(new String[authorities.size()]));
            return mongoUserDetails;
        } else {*/
            throw new UsernameNotFoundException("username not found");
       // }
    }
}
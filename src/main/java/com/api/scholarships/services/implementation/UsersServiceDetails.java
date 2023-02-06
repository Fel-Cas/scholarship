package com.api.scholarships.services.implementation;

import com.api.scholarships.entities.User;
import com.api.scholarships.entities.UserDetailsImp;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceDetails implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound=userService.getByEmail(username);
        return new UserDetailsImp(userFound);
    }
}

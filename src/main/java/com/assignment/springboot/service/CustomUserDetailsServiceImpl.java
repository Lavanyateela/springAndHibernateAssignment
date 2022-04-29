package com.assignment.springboot.service;

import com.assignment.springboot.entity.User;
import com.assignment.springboot.repository.CustomUserDetails;
import com.assignment.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);

        if (user==null)
        {
            throw new UsernameNotFoundException("User not found : "+username);
        }
        return new CustomUserDetails(user);
    }
}

package com.assignment.springboot.service;

import com.assignment.springboot.entity.User;
import com.assignment.springboot.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoadByUsername() {
        User user = new User(1, "test", "testpass", "ROLE_ADMIN");

       //when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        //Assert.assertEquals(user.getUsername(), userDetailsService.loadUserByUsername(user.getUsername()).getUsername());
    }
}

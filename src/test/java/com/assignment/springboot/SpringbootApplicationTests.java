package com.assignment.springboot;

import com.assignment.springboot.entity.Club;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.repository.ClubRepository;
import com.assignment.springboot.repository.UserRepository;
import com.assignment.springboot.service.ClubService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootApplicationTests {


}


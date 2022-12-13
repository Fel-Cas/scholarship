package com.api.scholarships.services;

import com.api.scholarships.entities.User;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.repositories.UserRepository;
import com.api.scholarships.services.implementation.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private UserMapper userMapper;
  @InjectMocks
  private UserServiceImp userService;
  private User user;

  @BeforeEach
  void init() {
    user = User.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("1001233147")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .updatedAt(Instant.now())
        .createdAt(Instant.now())
        .build();
  }
}
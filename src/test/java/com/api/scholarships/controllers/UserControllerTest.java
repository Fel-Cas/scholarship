package com.api.scholarships.controllers;

import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class UserControllerTest {
  @MockBean
  private UserService userService;
  @MockBean
  private UserMapper userMapper;
  @Autowired
  private MockMvc mockMvc;
  private String url = "/api/v1/scholarships/users";


}
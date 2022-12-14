package com.api.scholarships.controllers;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.entities.User;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {
  @MockBean
  private UserService userService;
  @MockBean
  private UserMapper userMapper;
  @Autowired
  private MockMvc mockMvc;
  private String url = "/api/v1/scholarships/users";
  private User user;
  private UserDTOResponse userDTOResponse;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void init() {
    user = User.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .password("12345678")
        .updatedAt(Instant.parse("2021-05-01T00:00:00Z"))
        .createdAt(Instant.parse("2021-05-01T00:00:00Z"))
        .build();

    userDTOResponse = UserDTOResponse.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .updatedAt("2021-05-01T00:00:00Z")
        .createdAt("2021-05-01T00:00:00Z")
        .build();
  }

  @Test
  @DisplayName("Test UserController, test to save an user")
  void save() throws Exception {
    //given
    UserDTO userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .password("12345678")
        .build();

    given(userService.save(any(UserDTO.class))).willReturn(user);
    given(userMapper.userToUserDTOResponse(any(User.class))).willReturn(userDTOResponse);
    //when
    ResultActions response = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userDTO)));
    //then
    response.andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.surname").value(user.getSurname()))
        .andExpect(jsonPath("$.dni").value(user.getDni()))
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(user.getUpdatedAt().toString()));
  }


}
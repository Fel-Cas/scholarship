package com.api.scholarships.controllers;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.dtos.UserUpdateDTO;
import com.api.scholarships.entities.Role;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
  @MockBean
  private UserService userService;
  @MockBean
  private UserMapper userMapper;
  @Autowired
  private MockMvc mockMvc;
  private String url = "/api/v1/scholarships/users";
  private User user;
  private Role role;
  private UserDTOResponse userDTOResponse;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void init() {
    role = Role.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();

    user = User.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .role(role)
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
        .role(role)
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
        .role(role.getNameRole())
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
        .andExpect(jsonPath("$.role.nameRole").value(user.getRole().getNameRole()))
        .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(user.getUpdatedAt().toString()));
  }

  @Test
  @DisplayName("Test UserController, test to find all users")
  void findAll() throws Exception {
    //given
    UserResponse userResponse = UserResponse.builder()
        .content(List.of(userDTOResponse))
        .numberPage(0)
        .totalPages(1)
        .totalElements(1L)
        .sizePage(10)
        .lastOne(true)
        .build();

    given(userService.getAll(any(Integer.class), any(Integer.class),anyString(),anyString())).willReturn(userResponse);
    //when
    ResultActions response = mockMvc.perform(get(url));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.content.size()").value(userResponse.getContent().size()))
        .andExpect(jsonPath("$.numberPage").value(userResponse.getNumberPage()))
        .andExpect(jsonPath("$.totalPages").value(userResponse.getTotalPages()))
        .andExpect(jsonPath("$.totalElements").value(userResponse.getTotalElements()))
        .andExpect(jsonPath("$.sizePage").value(userResponse.getSizePage()))
        .andExpect(jsonPath("$.lastOne").value(userResponse.isLastOne()));
  }

  @Test
  @DisplayName("Test UserController, test to find an user by id")
  void findById() throws Exception {
    //given
    given(userService.getById(any(Long.class))).willReturn(user);
    given(userMapper.userToUserDTOResponse(any(User.class))).willReturn(userDTOResponse);
    //when
    ResultActions response = mockMvc.perform(get(url + "/{id}", 1L));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.surname").value(user.getSurname()))
        .andExpect(jsonPath("$.dni").value(user.getDni()))
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.role.nameRole").value(user.getRole().getNameRole()))
        .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(user.getUpdatedAt().toString()));
  }

  @Test
  @DisplayName("Test UserController, test to find an user by dni")
  void findByDni() throws Exception {
    //given
    given(userService.getByDNI(any(String.class))).willReturn(user);
    given(userMapper.userToUserDTOResponse(any(User.class))).willReturn(userDTOResponse);
    //when
    ResultActions response = mockMvc.perform(get(url + "/dni/{dni}", userDTOResponse.getDni()));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.surname").value(user.getSurname()))
        .andExpect(jsonPath("$.dni").value(user.getDni()))
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.role.nameRole").value(user.getRole().getNameRole()))
        .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(user.getUpdatedAt().toString()));
  }

  @Test
  @DisplayName("Test UserController, test to update an user")
  void update() throws Exception {
    //given
    UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .build();

    given(userService.update(any(Long.class), any(UserUpdateDTO.class))).willReturn(user);
    given(userMapper.userToUserDTOResponse(any(User.class))).willReturn(userDTOResponse);
    //when
    ResultActions response = mockMvc.perform(put(url + "/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userUpdateDTO)));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.surname").value(user.getSurname()))
        .andExpect(jsonPath("$.dni").value(user.getDni()))
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.role.nameRole").value(user.getRole().getNameRole()))
        .andExpect(jsonPath("$.createdAt").value(user.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(user.getUpdatedAt().toString()));
  }

  @Test
  @DisplayName("Test UserController, test to delete an user")
  void deleteUser() throws Exception {
    //given
    doNothing().when(userService).delete(any(Long.class));
    //when
    ResultActions response = mockMvc.perform(delete(url + "/{id}", 1L));
    //then
    response.andExpect(status().isNoContent())
        .andDo(print());
  }
}
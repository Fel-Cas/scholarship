package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.repositories.UserRepository;
import com.api.scholarships.services.implementation.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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
  private UserDTO userDTO;

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
    userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("1001233147")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .build();
  }

  @Test
  @DisplayName("Test UserService,Test to save a new user")
  void testSaveUser() {
    //given
    given(userRepository.existsByEmail(anyString())).willReturn(false);
    given(userRepository.existsByDni(anyString())).willReturn(false);
    given(userRepository.save(any(User.class))).willReturn(user);
    given(userMapper.userDTOToUser(any(UserDTO.class))).willReturn(user);
    //when
    User userSaved = userService.save(userDTO);
    //then
    assertAll(
        () -> assertNotNull(userSaved),
        () -> assertEquals(userSaved.getName(), userDTO.getName()),
        () -> assertEquals(userSaved.getSurname(), userDTO.getSurname()),
        () -> assertEquals(userSaved.getDni(), userDTO.getDni()),
        () -> assertEquals(userSaved.getEmail(), userDTO.getEmail()),
        () -> assertEquals(userSaved.getPassword(), userDTO.getPassword()),
        ()-> assertNotNull(userSaved.getCreatedAt()),
        ()-> assertNotNull(userSaved.getUpdatedAt())
    );
  }

  @Test
  @DisplayName("Test UserService,Test to verify error when trying to save a user with an  already registered email")
  void testSaveUserWithAlreadyRegisteredEmail() {
    //given
    given(userRepository.existsByEmail(anyString())).willReturn(true);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.save(userDTO));
    //then
    assertEquals(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL, exception.getMessage());
  }

  @Test
  @DisplayName("Test UserService,Test to verify error when trying to save a user with a dni  already registered")
  void testSaveUserWithAlreadyRegisteredDni() {
    //given
    given(userRepository.existsByEmail(anyString())).willReturn(false);
    given(userRepository.existsByDni(anyString())).willReturn(true);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.save(userDTO));
    //then
    assertEquals(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_DNI, exception.getMessage());
  }

  @Test
  @DisplayName("Test UserService,Test to find all users")
  void testFindAllUsers() {
    //given
    UserDTOResponse userDTOResponse= UserDTOResponse.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("1001233147")
        .email("andres.cmonsalve@gmail.com")
        .updatedAt(Instant.now().toString())
        .createdAt(Instant.now().toString())
        .build();


    Page<User> users=new PageImpl<>(List.of(user));
    given(userRepository.findAll(any(Pageable.class))).willReturn(users);
    given(userMapper.userToUserDTOResponse(List.of(user))).willReturn(List.of(userDTOResponse));

    //when
    UserResponse usersFound = userService.getAll(0, 10, "id", "ASC");

    //then
    assertAll(
        () -> assertNotNull(usersFound),
        () -> assertThat(usersFound.getContent().size()).isGreaterThan(0),
        ()->assertEquals(0,usersFound.getNumberPage()),
        ()->assertEquals(1,usersFound.getSizePage()),
        ()->assertEquals(1,usersFound.getTotalPages()),
        ()->assertEquals(1,usersFound.getTotalElements()),
        ()->assertTrue(usersFound.isLastOne())
    );
  }
}
package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.dtos.UserUpdateDTO;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        .dni("123456789")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .updatedAt(Instant.now())
        .createdAt(Instant.now())
        .build();
  }

  @Test
  @DisplayName("Test UserService,Test to save a new user")
  void testSaveUser() {
    //given
    UserDTO userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("123456789")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .build();

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
    UserDTO userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("123456789")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .build();

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
    UserDTO userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("123456789")
        .email("andres.cmonsalve@gmail.com")
        .password("123456")
        .build();

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
        .dni("123456789")
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

  @Test
  @DisplayName("Test UserService, test to find a user by id")
  void testFindUserById() {
    //given
    given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
    //when
    User userFound=userService.getById(1L);
    //then
    assertNotNull(userFound);
    assertThat(userFound.getId()).isGreaterThan(0);
    assertEquals(userFound.getId(),user.getId());
  }

  @Test
  @DisplayName("Test UserService, test to find a user by id and verify error when user not found")
  void testFindUserByIdAndVerifyErrorWhenUserNotFound() {
    //given
    given(userRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getById(1L));
    //then
    assertEquals(Messages.MESSAGE_USER_NOT_FOUND.formatted(1L), exception.getMessage());
  }

  @Test
  @DisplayName("Test UserService, test to find user by dni")
  void testFindUserByDni() {
    //given
    given(userRepository.findByDni(anyString())).willReturn(Optional.of(user));
    //when
    User userFound=userService.getByDNI(user.getDni());
    //then
    assertNotNull(userFound);
    assertThat(userFound.getId()).isGreaterThan(0);
    assertEquals(userFound.getId(),user.getId());
  }

  @Test
  @DisplayName("Test UserService, test to find user by dni and verify error when user not found")
  void testFindUserByDniAndVerifyErrorWhenUserNotFound() {
    //given
    given(userRepository.findByDni(anyString())).willReturn(Optional.empty());
    //when
    NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getByDNI(user.getDni()));
    //then
    assertEquals(Messages.MESSAGE_USER_NOT_FOUND_BY_DNI.formatted(user.getDni()), exception.getMessage());
  }

  @Test
  @DisplayName("Test UserService, test to update user")
  void testUpdateUser() {
    //given
    UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("123456789")
        .email("andres.cmonsalve@gmail.com")
        .build();

    given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
    given(userRepository.existsByEmailAndIdNot(anyString(),anyLong())).willReturn(false);
    given(userRepository.existsByDniAndIdNot(anyString(),anyLong())).willReturn(false);
    given(userRepository.save(any(User.class))).willReturn(user);

    //when
    User userUpdated=userService.update(1L,userUpdateDTO);
    //then
    assertAll(
        ()-> assertNotNull(userUpdated),
        ()-> assertEquals(userUpdated.getId(),user.getId()),
        ()-> assertEquals(userUpdated.getName(),userUpdateDTO.getName()),
        ()-> assertEquals(userUpdated.getSurname(),userUpdateDTO.getSurname()),
        ()-> assertEquals(userUpdated.getDni(),userUpdateDTO.getDni()),
        ()-> assertEquals(userUpdated.getEmail(),userUpdateDTO.getEmail())
    );

  }

  @Test
  @DisplayName("Test UserService, test to delete a user")
  void testDelete(){
    //given
    given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
    willDoNothing().given(userRepository).delete(any(User.class));
    //when
    userService.delete(1L);
    //then
    verify(userRepository,times(1)).delete(any(User.class));
  }
}
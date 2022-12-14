package com.api.scholarships.mappers;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

  private UserMapper userMapper= Mappers.getMapper(UserMapper.class);

  @Test
  @DisplayName("Test userToUserDTOResponse")
  void userToUserDTOResponse() {
    //given
    User user = User.builder()
        .id(1L)
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .password("12345678")
        .updatedAt(Instant.parse("2021-05-01T00:00:00Z"))
        .createdAt(Instant.parse("2021-05-01T00:00:00Z"))
        .build();
    //when
    UserDTOResponse userDTOResponse = userMapper.userToUserDTOResponse(user);
    //then
    assertAll(
        () -> assertEquals(user.getId(), userDTOResponse.getId()),
        () -> assertEquals(user.getName(), userDTOResponse.getName()),
        () -> assertEquals(user.getSurname(), userDTOResponse.getSurname()),
        () -> assertEquals(user.getDni(), userDTOResponse.getDni()),
        () -> assertEquals(user.getEmail(), userDTOResponse.getEmail()),
        () -> assertEquals(user.getUpdatedAt().toString(), userDTOResponse.getUpdatedAt()),
        () -> assertEquals(user.getCreatedAt().toString(), userDTOResponse.getCreatedAt())
    );

  }

  @Test
  @DisplayName("Test userDTOToUser")
  void userDTOToUser() {
    //given
    UserDTO userDTO = UserDTO.builder()
        .name("Andrés Felipe")
        .surname("Castro Monsalve")
        .dni("12345678912")
        .email("andres.cmonsalve@gmail.com")
        .password("12345678")
        .build();
    //when
    User user = userMapper.userDTOToUser(userDTO);
    //then
    assertAll(
        () -> assertEquals(userDTO.getName(), user.getName()),
        () -> assertEquals(userDTO.getSurname(), user.getSurname()),
        () -> assertEquals(userDTO.getDni(), user.getDni()),
        () -> assertEquals(userDTO.getEmail(), user.getEmail()),
        () -> assertEquals(userDTO.getPassword(), user.getPassword())
    );
  }
}
package com.api.scholarships.mappers;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

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
  @DisplayName("Test list of users to list of userDTOResponse")
  void userToUserDTOResponseList() {
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

    List<User> users = List.of(user);
    //when
    List<UserDTOResponse> usersDTOResponse = userMapper.userToUserDTOResponse(users);
    //then
    assertAll(
        ()->assertEquals(usersDTOResponse.size(),users.size()),
        () -> assertEquals(users.get(0).getId(), usersDTOResponse.get(0).getId()),
        () -> assertEquals(users.get(0).getName(), usersDTOResponse.get(0).getName()),
        () -> assertEquals(users.get(0).getSurname(), usersDTOResponse.get(0).getSurname()),
        () -> assertEquals(users.get(0).getDni(), usersDTOResponse.get(0).getDni()),
        () -> assertEquals(users.get(0).getEmail(), usersDTOResponse.get(0).getEmail()),
        () -> assertEquals(users.get(0).getUpdatedAt().toString(), usersDTOResponse.get(0).getUpdatedAt()),
        () -> assertEquals(users.get(0).getCreatedAt().toString(), usersDTOResponse.get(0).getCreatedAt())
    );
  }
}
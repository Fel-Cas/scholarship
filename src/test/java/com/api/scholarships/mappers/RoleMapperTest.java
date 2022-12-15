package com.api.scholarships.mappers;

import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.entities.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

  private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

  @Test
  @DisplayName("Test RoleMapper,Test roleToRoleDTO")
  void roleToRoleDTO() {
    //given
    Role role= Role.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();
    //when
    RoleDTO roleDTO = roleMapper.roleToRoleDTO(role);
    //then
    assertAll(
        () -> assertEquals(role.getId(), roleDTO.getId()),
        () -> assertEquals(role.getNameRole(), roleDTO.getNameRole())
    );
  }

}
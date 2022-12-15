package com.api.scholarships.mappers;

import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.entities.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

  @Test
  @DisplayName("Test RoleMapper,Test roleToRoleDTOList")
  void roleToRoleDTOList() {
    //given
    Role role= Role.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();

    List<Role> roles = List.of(role);
    //when
    List<RoleDTO> roleDTO = roleMapper.roleToRoleDTO(roles);
    //then
    assertAll(
        ()->assertEquals(roles.size(),roleDTO.size()),
        () -> assertEquals(roles.get(0).getId(), roleDTO.get(0).getId()),
        () -> assertEquals(roles.get(0).getNameRole(), roleDTO.get(0).getNameRole())
    );
  }
}
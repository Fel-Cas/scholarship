package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.repositories.RoleRepository;
import com.api.scholarships.services.implementation.RoleServiceImp;
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


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class RoleServiceTest {

  @Mock
  private RoleRepository roleRepository;
  @Mock
  private RoleMapper roleMapper;
  @InjectMocks
  private RoleServiceImp roleService;
  private Role role;

  @BeforeEach
  void setUp() {
    role = Role.builder()
      .id(1L)
      .nameRole("ROLE_USER")
      .build();
  }

  @Test
  @DisplayName("Test RoleService, test to find a role by name")
  void findByName() {
    //given
    given(roleRepository.findByNameRole(anyString())).willReturn(Optional.of(role));
    //when
    Role roleFound = roleService.findByName(role.getNameRole());
    //then
    assertNotNull(roleFound);
    assertEquals(role.getNameRole(), roleFound.getNameRole());
    assertEquals(role.getId(), roleFound.getId());
  }

  @Test
  @DisplayName("Test RoleService, test to find a role by name and verify if the role is not found")
  void failFindByName() {
    //given
    given(roleRepository.findByNameRole(anyString())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> roleService.findByName(role.getNameRole()));
    //then
    assertEquals(Messages.MESSAGE_ROLE_NOT_FOUND_BY_NAME.formatted(role.getNameRole()), notFoundException.getMessage());
  }

  @Test
  @DisplayName("Test RoleService, test to find a role by id")
  void findById() {
    //given
    given(roleRepository.findById(anyLong())).willReturn(Optional.of(role));
    //when
    Role roleFound = roleService.findById(role.getId());
    //then
    assertNotNull(roleFound);
    assertEquals(role.getNameRole(), roleFound.getNameRole());
    assertEquals(role.getId(), roleFound.getId());
  }

  @Test
  @DisplayName("Test RoleService, test to find a role by id and verify if the role is not found")
  void failFindById() {
    //given
    given(roleRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> roleService.findById(role.getId()));
    //then
    assertEquals(Messages.MESSAGE_ROLE_NOT_FOUND.formatted(role.getId()), notFoundException.getMessage());
  }

  @Test
  @DisplayName("Test RoleService, test to find all roles")
  void findAll() {
    //given
    RoleDTO roleDTO = RoleDTO.builder()
      .id(1L)
      .nameRole("ROLE_USER")
      .build();

    Page<Role> roles=new PageImpl<>(List.of(role));
    given(roleRepository.findAll(any(Pageable.class))).willReturn(roles);
    given(roleMapper.roleToRoleDTO(List.of(role))).willReturn(List.of(roleDTO));
    //when
    RoleResponse rolesFound = roleService.findAll(0, 10, "id", "ASC");
    //then
    assertAll(
      () -> assertNotNull(rolesFound),
      ()->assertThat(rolesFound.getContent().size()).isGreaterThan(0),
        ()->assertEquals(0,rolesFound.getNumberPage()),
        ()->assertEquals(1,rolesFound.getSizePage()),
        ()->assertEquals(1,rolesFound.getTotalPages()),
        ()->assertEquals(1,rolesFound.getTotalElements()),
        ()->assertTrue(rolesFound.isLastOne())
    );

  }
}
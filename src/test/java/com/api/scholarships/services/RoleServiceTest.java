package com.api.scholarships.services;

import com.api.scholarships.entities.Role;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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
}
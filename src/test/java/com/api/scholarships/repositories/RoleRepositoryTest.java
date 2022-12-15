package com.api.scholarships.repositories;

import com.api.scholarships.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {
  @Autowired
  private RoleRepository roleRepository;
  private Role role;

  @BeforeEach
  void init(){
    role = Role.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();
  }

  @Test
  @DisplayName("Test RoleRepository,Test save a new role")
  void testSaveRole(){
    //given
    //when
    Role roleSaved = roleRepository.save(role);
    //then
    assertNotNull(roleSaved);
    assertThat(roleSaved.getId()).isGreaterThan(0);
    assertEquals(roleSaved.getNameRole(),role.getNameRole());
  }
  @Test
  @DisplayName("Test RoleRepository,Test find all roles")
  void testFindAllRoles(){
    //given
    roleRepository.save(role);
    //when
    List<Role>rolesFound = roleRepository.findAll();
    //then
    assertThat(roleRepository.findAll()).isNotEmpty();
    assertThat(rolesFound.size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test RoleRepository,Test find role by id")
  void testFindRoleById(){
    //given
    Role roleSaved=roleRepository.save(role);
    //when
    Optional<Role> roleFound = roleRepository.findById(roleSaved.getId());
    //then
    assertThat(roleFound.isPresent()).isTrue();
    assertEquals(roleSaved.getId(),roleFound.get().getId());
    assertEquals(roleSaved.getNameRole(),roleFound.get().getNameRole());

  }

  @Test
  @DisplayName("Test RoleRepository,Test find role by name")
  void testFindRoleByName(){
    //given
    Role roleSaved=roleRepository.save(role);
    //when
    Optional<Role> roleFound = roleRepository.findByNameRole(roleSaved.getNameRole());
    //then
    assertThat(roleFound.isPresent()).isTrue();
    assertEquals(roleSaved.getId(),roleFound.get().getId());
    assertEquals(roleSaved.getNameRole(),roleFound.get().getNameRole());
  }

  @Test
  @DisplayName("Test RoleRepository,Test delete a role ")
  void testDeleteRole(){
    //given
    Role roleSaved=roleRepository.save(role);
    //when
    roleRepository.delete(roleSaved);
    //then
    assertThat(roleRepository.findById(roleSaved.getId()).isPresent()).isFalse();
  }
}
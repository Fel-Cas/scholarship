package com.api.scholarships.controllers;

import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.services.interfaces.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
class RoleControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private RoleService roleService;
  @MockBean
  private RoleMapper roleMapper;
  private String url = "/api/v1/scholarships/roles";
  private RoleDTO roleDTO;
  private Role role;

  @BeforeEach
  void init() {
    roleDTO = RoleDTO.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();
    role = Role.builder()
        .id(1L)
        .nameRole("ROLE_USER")
        .build();
  }

  @Test
  @DisplayName("Test RoleController, test to find a role by id")
  void testFindRoleById() throws Exception {
    // given
    given(roleService.findById(anyLong())).willReturn(role);
    given(roleMapper.roleToRoleDTO(any(Role.class))).willReturn(roleDTO);
    // when
    ResultActions resultActions = mockMvc.perform(get(url + "/{id}", 1L));
    // then
    resultActions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.nameRole").value("ROLE_USER"));
  }

  @Test
  @DisplayName("Test RoleController, test to find all roles")
  void testFindAllRoles() throws Exception {
    // given
    RoleResponse roleResponse= RoleResponse.builder()
        .content(List.of(roleDTO))
        .numberPage(0)
        .sizePage(10)
        .lastOne(true)
        .totalPages(1)
        .totalElements(1L)
        .build();

    given(roleService.findAll(anyInt(), anyInt(), anyString(), anyString())).willReturn(roleResponse);
    // when
    ResultActions resultActions = mockMvc.perform(get(url));
    // then
    resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.content.size()").value(roleResponse.getContent().size()))
        .andExpect(jsonPath("$.numberPage").value(roleResponse.getNumberPage()))
        .andExpect(jsonPath("$.totalPages").value(roleResponse.getTotalPages()))
        .andExpect(jsonPath("$.totalElements").value(roleResponse.getTotalElements()))
        .andExpect(jsonPath("$.sizePage").value(roleResponse.getSizePage()))
        .andExpect(jsonPath("$.lastOne").value(roleResponse.isLastOne()));
  }

}
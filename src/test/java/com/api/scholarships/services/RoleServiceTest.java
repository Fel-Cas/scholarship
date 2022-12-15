package com.api.scholarships.services;

import com.api.scholarships.entities.Role;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.repositories.RoleRepository;
import com.api.scholarships.services.implementation.RoleServiceImp;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
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


}
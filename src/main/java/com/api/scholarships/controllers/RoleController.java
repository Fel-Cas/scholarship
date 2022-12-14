package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.ROLES)
public class RoleController {
  @Autowired
  private RoleService roleService;
  @Autowired
  private RoleMapper roleMapper;

  @GetMapping(Endpoints.ID)
  public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id){
    return ResponseEntity.ok(roleMapper.roleToRoleDTO(roleService.findById(id)));
  }
}

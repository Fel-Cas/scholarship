package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping()
  public ResponseEntity<RoleResponse> getRoles(
      @RequestParam(name ="numberPage" ,defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE,required = false) int page,
      @RequestParam(name = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(name = "sort",defaultValue = PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(name = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String order
  ){
    return ResponseEntity.ok(roleService.findAll(page,size,sort,order));
  }
}

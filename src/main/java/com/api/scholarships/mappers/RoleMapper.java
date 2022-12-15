package com.api.scholarships.mappers;

import com.api.scholarships.dtos.RoleDTO;
import com.api.scholarships.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
  RoleDTO roleToRoleDTO(Role role);
  List<RoleDTO> roleToRoleDTO(Iterable<Role> roles);
}

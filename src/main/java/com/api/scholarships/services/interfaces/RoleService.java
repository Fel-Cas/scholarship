package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;

public interface RoleService {
  Role findByName(String name);
  Role findById(Long id);
  RoleResponse findAll(int page, int size,String sort, String order);
}

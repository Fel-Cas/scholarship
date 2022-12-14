package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.services.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public RoleResponse findAll(int page, int size, String sort, String order) {
        return null;
    }
}


package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.repositories.RoleRepository;
import com.api.scholarships.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        Optional<Role> roleFound = roleRepository.findByName(name.toUpperCase());
        if(roleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ROLE_NOT_FOUND.formatted(name.toUpperCase()));
        }
        return roleFound.get();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> roleFound=roleRepository.findById(id);
        if(roleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ROLE_NOT_FOUND.formatted(id));
        }
        return roleFound.get();
    }

    @Override
    public RoleResponse findAll(int page, int size, String sort, String order) {
        return null;
    }
}


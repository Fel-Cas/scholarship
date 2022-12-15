package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.RoleResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.RoleMapper;
import com.api.scholarships.repositories.RoleRepository;
import com.api.scholarships.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role findByName(String name) {
        Optional<Role> roleFound = roleRepository.findByNameRole(name.toUpperCase());
        if(roleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ROLE_NOT_FOUND_BY_NAME.formatted(name.toUpperCase()));
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
        Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sort).ascending():Sort.by(sort).descending();
        Pageable pageable= PageRequest.of(page,size,sortDirection);
        Page<Role> rolesFond=roleRepository.findAll(pageable);
        return RoleResponse
                .builder()
                .content(roleMapper.roleToRoleDTO(rolesFond.getContent()))
                .numberPage(rolesFond.getNumber())
                .sizePage(rolesFond.getSize())
                .totalElements(rolesFond.getTotalElements())
                .totalPages(rolesFond.getTotalPages())
                .lastOne(rolesFond.isLast())
                .build();
    }
}


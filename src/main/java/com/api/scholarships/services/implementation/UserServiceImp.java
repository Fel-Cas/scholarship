package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.dtos.UserUpdateDTO;
import com.api.scholarships.entities.Role;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.repositories.UserRepository;
import com.api.scholarships.services.interfaces.RoleService;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RoleService roleService;

  @Override
  public User save(UserDTO userDTO) {
    if(userRepository.existsByEmail(userDTO.getEmail())) throw new BadRequestException(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL);
    if(userRepository.existsByDni(userDTO.getDni())) throw new BadRequestException(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_DNI);
    Role roleFound=roleService.findByName(userDTO.getRole());
    return userRepository.save(User.builder()
            .name(userDTO.getName())
            .surname(userDTO.getSurname())
            .email(userDTO.getEmail())
            .dni(userDTO.getDni())
            .password(userDTO.getPassword())
            .role(roleFound)
            .build());
  }

  @Override
  public UserResponse getAll(int page, int size, String sort, String order) {
    Sort sortDirection = order.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sort).ascending() : Sort.by(sort).descending();
    Page<User> usersFound = userRepository.findAll(PageRequest.of(page, size, sortDirection));
    return UserResponse
        .builder()
        .content(userMapper.userToUserDTOResponse(usersFound.getContent()))
        .numberPage(usersFound.getNumber())
        .sizePage(usersFound.getSize())
        .totalElements(usersFound.getTotalElements())
        .totalPages(usersFound.getTotalPages())
        .lastOne(usersFound.isLast())
        .build();
  }

  @Override
  public User getById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Messages.MESSAGE_USER_NOT_FOUND.formatted(id)));
    return user;
  }

  @Override
  public User getByDNI(String dni) {
    User userFound = userRepository.findByDni(dni)
        .orElseThrow(() -> new NotFoundException(Messages.MESSAGE_USER_NOT_FOUND_BY_DNI.formatted(dni)));
    return userFound;
  }

  @Override
  public User update(Long id, UserUpdateDTO userUpdateDTO) {
    User userFound = getById(id);
    if(userRepository.existsByEmailAndIdNot(userUpdateDTO.getEmail(),id)) throw new BadRequestException(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL);
    if(userRepository.existsByDniAndIdNot(userUpdateDTO.getDni(),id)) throw new BadRequestException(Messages.MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_DNI);
    updateLegalRepresentativeData(userFound, userUpdateDTO);
    return userRepository.save(userFound);
  }

  @Override
  public void delete(Long id) {
    User userFound = getById(id);
    userRepository.delete(userFound);
  }

  protected void updateLegalRepresentativeData(User userFound, UserUpdateDTO userUpdateDTO) {
    userFound.setDni(userUpdateDTO.getDni());
    userFound.setName(userUpdateDTO.getName());
    userFound.setSurname(userUpdateDTO.getSurname());
    userFound.setEmail(userUpdateDTO.getEmail());
  }
}

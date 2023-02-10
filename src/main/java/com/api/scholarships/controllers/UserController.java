package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.dtos.UserUpdateDTO;
import com.api.scholarships.entities.User;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.USERS)
public class UserController {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserService userService;

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<UserDTOResponse> save(@Valid @RequestBody UserDTO userDTO){
    User user = userService.save(userDTO);
    return new ResponseEntity(userMapper.userToUserDTOResponse(user), HttpStatus.CREATED);
  }

  @GetMapping()
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<UserResponse> findAll(
      @RequestParam(value ="numberPage" ,defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int page,
      @RequestParam(value ="pagesize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(value ="sort" ,defaultValue =PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(value = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR,required = false) String order
  ){
    return ResponseEntity.ok(userService.getAll(page, size, sort, order));
  }

  @GetMapping(Endpoints.ID)
  public ResponseEntity<UserDTOResponse> findById(@PathVariable Long id){
    return ResponseEntity.ok(userMapper.userToUserDTOResponse(userService.getById(id)));
  }

  @GetMapping(Endpoints.USERS_DNI)
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<UserDTOResponse> findByDNI(@PathVariable String dni){
    return ResponseEntity.ok(userMapper.userToUserDTOResponse(userService.getByDNI(dni)));
  }

  @PutMapping(Endpoints.ID)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<UserDTOResponse> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO legalRepresentativeDTO){
    return ResponseEntity.ok(userMapper.userToUserDTOResponse(userService.update(id, legalRepresentativeDTO)));
  }

  @DeleteMapping(Endpoints.ID)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

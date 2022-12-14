package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserResponse;
import com.api.scholarships.dtos.UserUpdateDTO;
import com.api.scholarships.entities.User;

public interface UserService {
  public User save(UserDTO legalRepresentative);
  public UserResponse getAll(int page, int size, String sort, String order);
  public User getById(Long id);
  public User getByDNI(String dni);
  public User update(Long id, UserUpdateDTO legalRepresentative);
  public void delete(Long id);
}

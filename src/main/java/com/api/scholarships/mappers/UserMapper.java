package com.api.scholarships.mappers;

import com.api.scholarships.dtos.UserDTO;
import com.api.scholarships.dtos.UserDTOResponse;
import com.api.scholarships.entities.Role;
import com.api.scholarships.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTOResponse userToUserDTOResponse(User user);
  List<UserDTOResponse> userToUserDTOResponse(Iterable<User> legalRepresentative);
}

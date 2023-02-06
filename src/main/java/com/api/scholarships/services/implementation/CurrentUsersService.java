package com.api.scholarships.services.implementation;

import com.api.scholarships.entities.User;
import com.api.scholarships.repositories.UserRepository;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUsersService {

  @Autowired
  private UserRepository userRepository;

  public User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserEmail = authentication.getName();
    return userRepository.findByEmail(currentUserEmail).get();
  }
}

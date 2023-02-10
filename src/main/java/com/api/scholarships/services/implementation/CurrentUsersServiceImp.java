package com.api.scholarships.services.implementation;

import com.api.scholarships.services.interfaces.CurrentUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUsersServiceImp implements CurrentUserService {
  public String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}

package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.ForbiddenException;
import com.api.scholarships.services.interfaces.CurrentUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUsersServiceImp implements CurrentUserService {
  private String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  @Override
  public void verifyCorrectUser(User user) {
    if (!user.getEmail().equals(getCurrentUser())){
      throw new ForbiddenException(Messages.MESSAGE_ACCESS_DENIED);
    }
  }

  @Override
  public void verifyCorrectUserInCompany(Company company) {
    company.getUsers().stream()
        .filter(user -> user
            .getEmail()
            .equals(getCurrentUser()))
        .findFirst()
        .orElseThrow(() -> new ForbiddenException(Messages.MESSAGE_ACCESS_DENIED));
  }

  @Override
  public void verifyCorrectUserInScholarship(Scholarship schorlarship) {
    verifyCorrectUserInCompany(schorlarship.getCompany());
  }
}

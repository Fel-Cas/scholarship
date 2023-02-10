package com.api.scholarships.services.interfaces;

import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.User;

public interface CurrentUserService {
  void verifyCorrectUser(User user);
  void verifyCorrectUserInCompany(Company companyFound);
}

package com.api.scholarships.services.interfaces;

import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.User;

public interface CurrentUserService {
  void verifyCorrectUser(User user);
  void verifyCorrectUserInCompany(Company company);
  void verifyCorrectUserInScholarship(Scholarship schorlarship);
}

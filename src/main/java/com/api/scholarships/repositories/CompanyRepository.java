package com.api.scholarships.repositories;

import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
  boolean existsByName(String name);
  boolean existsByEmail(String email);
  boolean existsByPhone(String phone);
  boolean existsByNameAndIdNot(String name, Long id);
  boolean existsByEmailAndIdNot(String email, Long id);
  boolean existsByPhoneAndIdNot(String phone, Long id);
  boolean existsByUsers(User user);

}

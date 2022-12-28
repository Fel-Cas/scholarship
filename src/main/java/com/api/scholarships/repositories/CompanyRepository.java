package com.api.scholarships.repositories;

import com.api.scholarships.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
  boolean existsByName(String name);
  boolean existsByEmail(String email);
  boolean existsByPhone(String phone);

}

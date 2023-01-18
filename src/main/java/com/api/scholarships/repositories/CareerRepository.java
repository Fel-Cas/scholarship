package com.api.scholarships.repositories;

import com.api.scholarships.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career,Long> {
  boolean existByCareerName(String careerName);
}

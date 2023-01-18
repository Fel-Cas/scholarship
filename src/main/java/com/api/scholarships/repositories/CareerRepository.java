package com.api.scholarships.repositories;

import com.api.scholarships.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career,Long> {
  boolean existsByCareerName(String careerName);
  Optional<Career> findByCareerName(String careerName);
}

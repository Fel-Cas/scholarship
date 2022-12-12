package com.api.scholarships.repositories;

import com.api.scholarships.entities.LegalRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepresentativeRepository extends JpaRepository<LegalRepresentative, Long> {
  boolean existsByEmail(String email);
  boolean existsByDni(String dni);


}

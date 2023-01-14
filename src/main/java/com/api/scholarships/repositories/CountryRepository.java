package com.api.scholarships.repositories;

import com.api.scholarships.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
  boolean existsByCountryNameAndAbbreviation(String countryName, String abbreviation);
  boolean existsByCountryName(String countryName);
  boolean existsByAbbreviation(String abbreviation);
}

package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Country;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipCountry implements ScholarshipStrategy{

  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CountryService countryService;

  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.COUNTRY;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Country countryFound=countryService.findById(idCondition);
    return scholarshipRepository.findByCountry(countryFound, pageable);
  }
}

package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.constants.Variables;
import com.api.scholarships.entities.Country;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CountryService;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatusCountry implements ScholarshipStrategy{

  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CountryService countryService;
  @Autowired
  private StatusService statusService;

  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS_COUNTRY;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Status statusFound=statusService.findByName(Variables.STATUS_DEFAULT);
    Country countryFound=countryService.findById(idCondition);
    return scholarshipRepository.findByStatusAndCountry(statusFound, countryFound, pageable);
  }
}

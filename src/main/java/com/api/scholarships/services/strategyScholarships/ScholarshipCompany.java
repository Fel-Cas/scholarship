package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipCompany implements ScholarshipStrategy{
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CompanyService companyService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.COMPANY;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Company companyFound=companyService.getOne(idCondition);
    return scholarshipRepository.findByCompany(companyFound,pageable);
  }
}

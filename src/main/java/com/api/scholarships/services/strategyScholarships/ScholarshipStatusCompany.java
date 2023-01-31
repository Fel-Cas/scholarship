package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.constants.Variables;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CompanyService;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatusCompany implements ScholarshipStrategy {
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private StatusService statusService;
  @Autowired
  private CompanyService companyService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS_COMPANY;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Status statusFound=statusService.findByName(Variables.STATUS_DEFAULT);
    Company companyFound=companyService.getOne(idCondition);
    return scholarshipRepository.findByStatusAndCompany(statusFound,companyFound,pageable);
  }
}

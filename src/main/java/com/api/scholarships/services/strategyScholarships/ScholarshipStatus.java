package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatus implements ScholarshipStrategy{
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private StatusService statusService;

  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Status statusFound=statusService.findById(idCondition);
    return scholarshipRepository.findByStatus(statusFound, pageable);
  }
}

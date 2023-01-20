package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipDefault implements ScholarshipStrategy {
  @Autowired
  private ScholarshipRepository scholarshipRepository;

  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.DEFAULT;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    return scholarshipRepository.findAll(pageable);
  }
}

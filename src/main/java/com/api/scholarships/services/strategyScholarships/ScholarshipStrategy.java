package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Scholarship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScholarshipStrategy {
  public ScholarshipType typeStrategy();
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition);

}

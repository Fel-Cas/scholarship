package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Career;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipCareer implements ScholarshipStrategy{

  @Autowired
  private CareerService careerService;
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.CAREER;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Career careerFound=careerService.findById(idCondition);
    return scholarshipRepository.findByCareers(careerFound,pageable);
  }
}

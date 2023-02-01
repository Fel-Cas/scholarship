package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.constants.Variables;
import com.api.scholarships.entities.Career;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CareerService;
import com.api.scholarships.services.interfaces.StatusService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatusCareer implements ScholarshipStrategy {
  @Autowired
  private StatusService statusService;
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CareerService careerService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS_CAREER;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Career careerFound=careerService.findById(idCondition);
    Status statusFound=statusService.findByName(Variables.STATUS_DEFAULT);
    return scholarshipRepository.findByStatusAndCareers(statusFound,careerFound,pageable);
  }
}

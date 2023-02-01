package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.constants.Variables;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CourseTypeService;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatusCourseType implements ScholarshipStrategy{
  @Autowired
  private CourseTypeService courseTypeService;
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private StatusService statusService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS_COURSETYPE;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Status statusFound=statusService.findByName(Variables.STATUS_DEFAULT);
    CourseType courseTypeFound=courseTypeService.findById(idCondition);
    return scholarshipRepository.findByStatusAndCourseType(statusFound, courseTypeFound, pageable);
  }
}

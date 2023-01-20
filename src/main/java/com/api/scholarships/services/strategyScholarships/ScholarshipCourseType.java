package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.CourseType;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipCourseType implements ScholarshipStrategy{
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CourseTypeService courseTypeService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.COURSETYPE;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    CourseType courseTypeFound=courseTypeService.findById(idCondition);
    return scholarshipRepository.findByCourseType(courseTypeFound,pageable);
  }
}

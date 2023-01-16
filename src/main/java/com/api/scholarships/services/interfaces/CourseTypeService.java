package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;

public interface CourseTypeService {
  public CourseType findById(Long id);
  public CourseTypeResponse findAll(int page, int sizePage, String sort, String order);
  public CourseType findByCourseType(String courseType);

}

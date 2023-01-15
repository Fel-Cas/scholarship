package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTypeServiceImp implements CourseTypeService {

  @Autowired
  private CourseTypeRepository courseTypeRepository;

  @Override
  public CourseType findById(Long id) {
    return null;
  }

  @Override
  public CourseTypeResponse findAll(int page, int sizePage, String sort, String order) {
    return null;
  }
}

package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTypeServiceImp implements CourseTypeService {

  @Autowired
  private CourseTypeRepository courseTypeRepository;

  @Override
  public CourseType findById(Long id) {
    Optional<CourseType> courseFound=courseTypeRepository.findById(id);
    if(courseFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_COURSE_TYPE_NOT_FOUND.formatted(id));
    }
    return courseFound.get();
  }

  @Override
  public CourseTypeResponse findAll(int page, int sizePage, String sort, String order) {
    return null;
  }
}

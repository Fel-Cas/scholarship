package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTypeServiceImp implements CourseTypeService {

  @Autowired
  private CourseTypeRepository courseTypeRepository;
  @Autowired
  private CourseTypeMapper courseTypeMapper;

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
    Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sort).ascending():Sort.by(sort).descending();
    Pageable pageable=PageRequest.of(page, sizePage,sortDirection);
    Page<CourseType> courseTypesFound=courseTypeRepository.findAll(pageable);
    return CourseTypeResponse.builder()
        .content(courseTypeMapper.typeCoursesToTypeCourseDTOs(courseTypesFound.getContent()))
        .numberPage(courseTypesFound.getNumber())
        .totalPages(courseTypesFound.getTotalPages())
        .lastOne(courseTypesFound.isLast())
        .sizePage(courseTypesFound.getSize())
        .totalElements(courseTypesFound.getTotalElements())
        .build();
  }

  @Override
  public CourseType findByCourseType(String courseType) {
    Optional<CourseType> courseTypeFound=courseTypeRepository.findByCourseType(courseType);
    if(courseTypeFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_COURSE_TYPE_NOT_FOUND_BY_NAME.formatted(courseType));
    }
    return courseTypeFound.get();
  }
}

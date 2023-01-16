package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.COURSE_TYPE)
public class CourseTypeController {
  @Autowired
  private CourseTypeService courseTypeService;
  @Autowired
  private CourseTypeMapper courseTypeMapper;

  @GetMapping(Endpoints.ID)
  public ResponseEntity<CourseTypeDTO> findById(@PathVariable("id") Long id){
    CourseType courseFound= courseTypeService.findById(id);
    return ResponseEntity.ok(courseTypeMapper.typeCourseToTypeCourseDTO(courseFound));
  }
}

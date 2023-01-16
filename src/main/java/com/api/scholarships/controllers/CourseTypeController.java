package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping()
  public ResponseEntity<CourseTypeResponse> findAll(
      @RequestParam(name = "numberPage",defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE,required = false) int page,
      @RequestParam(name = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(name = "sort",defaultValue = PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(name = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR,required = false) String order
  ){
    return ResponseEntity.ok(courseTypeService.findAll(page,size,sort,order));
  }
}

package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.entities.CourseType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseTypeMapper {
  public CourseTypeDTO typeCourseToTypeCourseDTO(CourseType courseType);
  public List<CourseTypeDTO> typeCoursesToTypeCourseDTOs(Iterable<CourseType> typeCourses);

}

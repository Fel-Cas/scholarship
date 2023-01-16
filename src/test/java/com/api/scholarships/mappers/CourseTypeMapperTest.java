package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.entities.CourseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CourseTypeMapperTest {

  private CourseTypeMapper courseTypeMapper=Mappers.getMapper(CourseTypeMapper.class);
  private CourseType courseType;

  @BeforeEach
  void setUp(){
    courseType=CourseType.builder()
        .id(5L)
        .courseType("BOOTCAMP")
        .build();
  }

  @Test
  @DisplayName("Test CourseTypeMapper, test to pass from CourseType class to CourseTypeDTO class")
  void testCourseTypeMappertoCourseTypeDTO(){
    //given
    //when
    CourseTypeDTO courseTypeDTO=courseTypeMapper.typeCourseToTypeCourseDTO(courseType);
    //then
    assertNotNull(courseTypeDTO);
    assertEquals(courseType.getId(),courseTypeDTO.getId());
    assertEquals(courseType.getCourseType(),courseTypeDTO.getCourseType());
  }


}
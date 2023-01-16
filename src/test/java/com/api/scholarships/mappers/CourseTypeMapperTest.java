package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.entities.CourseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
  void testPassFromCourseTypetoCourseTypeDTO(){
    //given
    //when
    CourseTypeDTO courseTypeDTO=courseTypeMapper.typeCourseToTypeCourseDTO(courseType);
    //then
    assertNotNull(courseTypeDTO);
    assertEquals(courseType.getId(),courseTypeDTO.getId());
    assertEquals(courseType.getCourseType(),courseTypeDTO.getCourseType());
  }

  @Test
  @DisplayName("Test CourseTypeMapper, test to pass from a list of CourseType items to the list of CourseTypeDTO items.")
  void testPassFromCourseTypeListToCourseTypeDTOList(){
    //given
    List<CourseType> courseTypes=List.of(courseType);
    //when
    List<CourseTypeDTO> courseTypeDTOS=courseTypeMapper.typeCoursesToTypeCourseDTOs(courseTypes);
    //then
    assertAll(
        ()->assertEquals(courseTypes.size(),courseTypeDTOS.size()),
        ()->assertEquals(courseTypes.get(0).getId(),courseTypeDTOS.get(0).getId()),
        ()->assertEquals(courseTypes.get(0).getCourseType(),courseTypeDTOS.get(0).getCourseType())
    );
  }


}
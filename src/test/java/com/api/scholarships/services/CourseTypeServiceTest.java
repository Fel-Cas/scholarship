package com.api.scholarships.services;

import com.api.scholarships.entities.CourseType;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.implementation.CourseTypeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CourseTypeServiceTest {

  @Mock
  private CourseTypeRepository courseTypeRepository;
  @Mock
  private CourseTypeMapper courseTypeMapper;
  @InjectMocks
  private CourseTypeServiceImp courseTypeService;
  private CourseType courseType;

  @BeforeEach
  void setUp(){
    courseType=CourseType
        .builder()
        .id(1L)
        .courseType("CURSO CORTO")
        .build();
  }

  @Test
  @DisplayName("Test CourseTypeServce, test to find a course by its id")
  void testFindId(){
    //given
    given(courseTypeRepository.findById(anyLong())).willReturn(Optional.of(courseType));
    //when
    CourseType courseTypeFound=courseTypeService.findById(anyLong());
    //then
    assertNotNull(courseTypeFound);
    assertEquals(courseType.getId(),courseTypeFound.getId());
    assertEquals(courseType.getCourseType(),courseTypeFound.getCourseType());
  }
}
package com.api.scholarships.services;

import com.api.scholarships.entities.CourseType;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.implementation.CourseTypeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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
}
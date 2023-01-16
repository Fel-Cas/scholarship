package com.api.scholarships.controllers;

import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CourseTypeController.class)
@ActiveProfiles(profiles = "test")
class CourseTypeControllerTest {

  @Autowired
  private WebMvcTest clien;
  @MockBean
  private CourseTypeService courseTypeService;
  @MockBean
  private CourseTypeMapper courseTypeMapper;
  private String url="/api/v1/scholarships/course-types";

}
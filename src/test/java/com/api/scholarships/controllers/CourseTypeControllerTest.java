package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.services.interfaces.CourseTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CourseTypeController.class)
@ActiveProfiles(profiles = "test")
class CourseTypeControllerTest {

  @Autowired
  private MockMvc client;
  @MockBean
  private CourseTypeService courseTypeService;
  @MockBean
  private CourseTypeMapper courseTypeMapper;
  private String url="/api/v1/scholarships/course-types";

  @Test
  @DisplayName("Test CourseTypeController, test to find a course by id")
  void testFindById() throws Exception {
    //given
    CourseType courseType=CourseType.builder()
        .id(5L)
        .courseType("BOOTCAMP")
        .build();

    CourseTypeDTO courseTypeDTO= CourseTypeDTO.builder()
        .id(5L)
        .courseType("BOOTCAMP")
        .build();

    given(courseTypeService.findById(anyLong())).willReturn(courseType);
    given(courseTypeMapper.typeCourseToTypeCourseDTO(any(CourseType.class))).willReturn(courseTypeDTO);
    //when
    ResultActions response=client.perform(get(url+"/"+courseType.getId())
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(courseType.getId()))
        .andExpect(jsonPath("$.courseType").value(courseType.getCourseType()));
  }

}
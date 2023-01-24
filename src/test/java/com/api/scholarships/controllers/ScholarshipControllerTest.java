package com.api.scholarships.controllers;

import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.entities.*;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.services.interfaces.ScholarshipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScholarshipController.class)
class ScholarshipControllerTest {
  @MockBean
  private ScholarshipService scholarshipService;
  @MockBean
  private ScholarshipMapper scholarshipMapper;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc client;
  private String url="/api/v1/scholarships";
  private Scholarship scholarship;
  private ScholarshipDTOResponse scholarshipDTOResponse;
  private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  @BeforeEach
  void setUp() throws ParseException {
    List<Career> careers=new ArrayList<>();

    //Course type
    CourseType courseType= CourseType.builder()
        .id(1L)
        .courseType("BOOTCAMP")
        .build();
    //Country
    Country country= Country.builder()
        .id(1L)
        .countryName("COLOMBIA")
        .abbreviation("COL")
        .build();
    //status
    Status status= Status.builder()
        .id(1L)
        .statusName("ACTIVO")
        .build();
    //language
    Language language=Language.builder()
        .id(1L)
        .languageName("ESPAÑOL")
        .build();
    //image
    Image image=Image.builder()
        .id(1L)
        .url("https://localhost:8080/images/profile")
        .imageId("dsdsdsd")
        .name("profile.png")
        .build();
    //career
    Career career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
    Career career1=Career.builder()
        .id(2L)
        .careerName("MATEMATICAS")
        .build();
    careers.add(career);
    careers.add(career1);
    //company
    Company company=Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .image(image)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    scholarship=Scholarship.builder()
        .id(1L)
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate(format.parse("2023-01-01"))
        .finishDate(format.parse("2023-02-02"))
        .link("http:localhost:6788/admin/api/scholarships")
        .courseType(courseType)
        .country(country)
        .status(status)
        .language(language)
        .image(image)
        .company(company)
        .careers(careers)
        .build();

    scholarshipDTOResponse=ScholarshipDTOResponse.builder()
        .id(1L)
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate(format.parse("2023-01-01"))
        .finishDate(format.parse("2023-02-02"))
        .link("http:localhost:6788/admin/api/scholarships")
        .courseType(courseType)
        .country(country)
        .status(status)
        .language(language)
        .image(image)
        .company(company)
        .careers(careers)
        .build();
  }

  @Test
  @DisplayName("Test ScholarshipController, test to get one scholarship by id")
  void testGetOne() throws Exception {
    //given
    given(scholarshipService.getById(1L)).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(get(url+"/1").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(scholarship.getId()))
        .andExpect(jsonPath("$.title").value(scholarship.getTitle()))
        .andExpect(jsonPath("$.description").value(scholarship.getDescription()))
        .andExpect(jsonPath("$.link ").value(scholarship.getLink()))
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));

  }
}
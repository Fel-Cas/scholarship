package com.api.scholarships.controllers;

import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

  @Test
  @DisplayName("Test ScholarshipController, test to update one scholarship")
  void testUpdateOne() throws Exception {
    //given
    ScholarshipUpdateDTO scholarshipUpdateDTO=ScholarshipUpdateDTO.builder()
        .title("BIG DATA SCHOLARSHIP")
        .description("La beca de Big Data es una oportunidad de estudio y formación para aquellos interesados en adquirir habilidades y conocimientos en el campo de la gestión y análisis de datos masivos. Los beneficiarios de la beca tendrán la oportunidad de aprender técnicas y herramientas avanzadas para el manejo y análisis de datos, así como también la aplicación práctica de estas habilidades en proyectos reales. Además, la beca también puede incluir la posibilidad de trabajar en colaboración con profesionales y empresas líderes en el campo del Big Data.")
        .link("http://loclahost:9090/big-data")
        .startDate(format.parse("2022-11-12"))
        .finishDate(format.parse("2023-02-14 "))
        .build();

    scholarship.setTitle(scholarshipUpdateDTO.getTitle());
    scholarship.setDescription(scholarshipUpdateDTO.getDescription());
    scholarship.setLink(scholarshipUpdateDTO.getLink());
    scholarship.setStartDate(scholarshipUpdateDTO.getStartDate());
    scholarship.setFinishDate(scholarshipUpdateDTO.getFinishDate());

    scholarshipDTOResponse.setTitle(scholarshipUpdateDTO.getTitle());
    scholarshipDTOResponse.setDescription(scholarshipUpdateDTO.getDescription());
    scholarshipDTOResponse.setLink(scholarshipUpdateDTO.getLink());
    scholarshipDTOResponse.setStartDate(scholarshipUpdateDTO.getStartDate());
    scholarshipDTOResponse.setFinishDate(scholarshipUpdateDTO.getFinishDate());

    given(scholarshipService.update( scholarshipUpdateDTO,1L)).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(scholarshipUpdateDTO)));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(scholarship.getId()))
        .andExpect(jsonPath("$.title").value(scholarshipDTOResponse.getTitle()))
        .andExpect(jsonPath("$.description").value(scholarshipDTOResponse.getDescription()))
        .andExpect(jsonPath("$.link").value(scholarshipDTOResponse.getLink()))
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));
  }

  @Test
  @DisplayName("Test ScholarshipService, test to delete a scholarship")
  void testDelete() throws Exception {
    //given
    willDoNothing().given(scholarshipService).delete(1L);
    //when
    ResultActions response=client.perform(delete(url+"/1").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isNoContent())
        .andDo(print());
    verify(scholarshipService, times(1)).delete(1L);
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the country of a scholarship")
  void testChangeCountry() throws Exception {
    //given
    Country country=new Country(1L, "INGLATERRA","ING");
    scholarship.setCountry(country);
    scholarshipDTOResponse.setCountry(country);
    given(scholarshipService.changeCountry(1L,2L)).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/country/1/2").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(scholarship.getId()))
        .andExpect(jsonPath("$.title").value(scholarshipDTOResponse.getTitle()))
        .andExpect(jsonPath("$.description").value(scholarshipDTOResponse.getDescription()))
        .andExpect(jsonPath("$.link").value(scholarshipDTOResponse.getLink()))
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));
    verify(scholarshipService, times(1)).changeCountry(1L,2L);
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the course type of a scholarship")
  void testChangeCourseType() throws Exception {
    //given
    CourseType courseType=CourseType.builder().id(1L).courseType("BOOTCAMP").build();
    scholarship.setCourseType(courseType);
    scholarshipDTOResponse.setCourseType(courseType);
    given(scholarshipService.changeCourseType(anyLong(),anyLong())).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/course-type/1/2").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the status of a scholarship")
  void testChangeStatus() throws Exception {
    //given
    Status status=Status.builder().id(1L).statusName("ACTIVE").build();
    scholarship.setStatus(status);
    scholarshipDTOResponse.setStatus(status);
    given(scholarshipService.changeStatus(anyLong(),anyLong())).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/status/1/2").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the language of a scholarship")
  void testChangeLanguage() throws Exception {
    //given
    Language language=Language.builder().id(1L).languageName("ENGLISH").build();
    scholarship.setLanguage(language);
    scholarshipDTOResponse.setLanguage(language);
    given(scholarshipService.changeLanguage(anyLong(),anyLong())).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/language/1/2").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));

  }

  @Test
  @DisplayName("Test ScholarshipService, test to add a career to a scholarship")
  void testAddCareer() throws Exception {
    //given
    Career career=Career.builder().id(1L).careerName("ENTERPRISE").build();
    scholarship.getCareers().add(career);
    scholarshipDTOResponse.getCareers().add(career);
    given(scholarshipService.addCareer(anyLong(),anyLong())).willReturn(scholarship);
    given(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship)).willReturn(scholarshipDTOResponse);
    //when
    ResultActions response=client.perform(put(url+"/career/1/2?action=ADD").contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json(objectMapper.writeValueAsString(scholarshipDTOResponse)));
  }
}
package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.services.interfaces.CareerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class CareerControllerTest {
  @MockBean
  private CareerService careerService;
  @MockBean
  private CareerMapper careerMapper;
  @Autowired
  private MockMvc client;
  @Autowired
  private ObjectMapper objectMapper;
  private Career career;
  private CareerDTOResponse careerDTOResponse;

  @BeforeEach
  void setUp(){
    career=Career.builder()
        .id(1L)
        .careerName("INGENIRIA DE SISTEMAS")
        .build();

    careerDTOResponse=CareerDTOResponse.builder()
        .id(1L)
        .careerName("INGENIRIA DE SISTEMAS")
        .build();
  }
}
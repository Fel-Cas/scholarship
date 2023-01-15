package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.services.interfaces.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

  @MockBean
  private CountryService countryService;
  @MockBean
  private CountryMapper countryMapper;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc client;
  private String url="/api/v1/scholarships/countries";
  private Country country;
  private CountryDTOResponse countryDTOResponse;

  @BeforeEach
  void setUp() {
    country=Country.builder()
        .id(1L)
        .countryName("BRASIL")
        .abbreviation("BRA")
        .build();
    countryDTOResponse=CountryDTOResponse.builder()
        .id(1L)
        .countryName("BRASIL")
        .abbreviation("BRA")
        .build();
  }

}
package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.services.interfaces.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  @Test
  @DisplayName("Test CountryController, test to create a country")
  void testCreate() throws Exception {
    //given
    CountryDTO countryDTO=CountryDTO.builder()
        .countryName("Brasil")
        .abbreviation("Bra")
        .build();
    given(countryService.create(countryDTO)).willReturn(country);
    given(countryMapper.countryToCountryDTOResponse(country)).willReturn(countryDTOResponse);
    //when
    ResultActions response=client.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(countryDTO)));
    //then
    response
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(country.getId()))
        .andExpect(jsonPath("$.countryName").value(country.getCountryName()))
        .andExpect(jsonPath("$.abbreviation").value(country.getAbbreviation()));
  }


}
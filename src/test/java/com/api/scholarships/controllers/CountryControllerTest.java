package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.services.interfaces.CountryService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

  @Test
  @DisplayName("Test CountryController, test to find all countries")
  void testFindAll() throws Exception {
    //given
    CountryResponse countryResponse=CountryResponse.builder()
        .content(List.of(countryDTOResponse))
        .numberPage(0)
        .sizePage(10)
        .lastOne(true)
        .totalPages(1)
        .totalElements(1L)
        .build();
    given(countryService.findAll(anyInt(), anyInt(), anyString(), anyString())).willReturn(countryResponse);
    //when
    ResultActions response=client.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.content.size()").value(countryResponse.getContent().size()))
        .andExpect(jsonPath("$.numberPage").value(countryResponse.getNumberPage()))
        .andExpect(jsonPath("$.sizePage").value(countryResponse.getSizePage()))
        .andExpect(jsonPath("$.lastOne").value(countryResponse.isLastOne()))
        .andExpect(jsonPath("$.totalPages").value(countryResponse.getTotalPages()))
        .andExpect(jsonPath("$.totalElements").value(countryResponse.getTotalElements()));
  }

  @Test
  @DisplayName("Test CountryController, test to find a country by id")
  void testFindById() throws Exception {
    //given
    given(countryService.findById(anyLong())).willReturn(country);
    given(countryMapper.countryToCountryDTOResponse(country)).willReturn(countryDTOResponse);
    //when
    ResultActions response=client.perform(get(url+"/"+country.getId())
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(country.getId()))
        .andExpect(jsonPath("$.countryName").value(country.getCountryName()))
        .andExpect(jsonPath("$.abbreviation").value(country.getAbbreviation()));
  }

  @Test
  @DisplayName("Test CountryController, test to delete a country")
  void testDelete() throws Exception {
    //given
    willDoNothing().given(countryService).delete(anyLong());
    //when
    ResultActions response=client.perform(delete(url+"/"+country.getId())
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isNoContent())
        .andDo(print());
  }
}
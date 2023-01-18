package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.services.interfaces.CareerService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CareerController.class)
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
  private String url="/api/v1/scholarships/careers";

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

  @Test
  @DisplayName("Test CareerController, test to create a career")
  void testPostCareer() throws Exception {
    //given
    CareerDTO careerDTO= new CareerDTO("INGENIERIA DE SISTEMAS");
    given(careerService.create(careerDTO)).willReturn(career);
    given(careerMapper.careerToCareerDTOResponse(career)).willReturn(careerDTOResponse);
    //when
    ResultActions response=client.perform(post(url).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(careerDTO)));
    //then
    response
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(career.getId()))
        .andExpect(jsonPath("$.careerName").value(career.getCareerName()))
        .andExpect(content().json(objectMapper.writeValueAsString(careerDTOResponse)));
  }

  @Test
  @DisplayName("Test CareerController, test to find a career by id")
  void testFindCareerById() throws Exception {
    //given
    given(careerService.findById(anyLong())).willReturn(career);
    given(careerMapper.careerToCareerDTOResponse(any(Career.class))).willReturn(careerDTOResponse);
    //when
    ResultActions response=client.perform(get(url+"/"+career.getId()).contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(career.getId()))
        .andExpect(jsonPath("$.careerName").value(career.getCareerName()))
        .andExpect(content().json(objectMapper.writeValueAsString(careerDTOResponse)));
  }

  @Test
  @DisplayName("Test CareerController, test to find all careers")
  void testFindAllCareers() throws Exception {
    //given
    CareerResponse careerResponse=CareerResponse.builder()
        .content(List.of(careerDTOResponse))
        .numberPage(0)
        .totalPages(1)
        .totalElements(1L)
        .sizePage(10)
        .lastOne(true)
        .build();
    given(careerService.findAll(anyInt(), anyInt(), anyString(), anyString())).willReturn(careerResponse);
    //when
    ResultActions response=client.perform(get(url).contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.content.size()").value(careerResponse.getContent().size()))
        .andExpect(jsonPath("$.numberPage").value(careerResponse.getNumberPage()))
        .andExpect(jsonPath("$.totalPages").value(careerResponse.getTotalPages()))
        .andExpect(jsonPath("$.totalElements").value(careerResponse.getTotalElements()))
        .andExpect(jsonPath("$.sizePage").value(careerResponse.getSizePage()))
        .andExpect(jsonPath("$.lastOne").value(careerResponse.isLastOne()))
        .andExpect(content().json(objectMapper.writeValueAsString(careerResponse)));
  }

  @Test
  @DisplayName("Test CareerController, test to delete a career")
  void testDeleteCareer() throws Exception {
    //given
    willDoNothing().given(careerService).delete(career.getId());
    //when
    ResultActions response=client.perform(delete(url+"/"+career.getId())
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isNoContent());
  }
}
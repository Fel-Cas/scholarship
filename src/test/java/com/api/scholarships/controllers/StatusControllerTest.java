package com.api.scholarships.controllers;

import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.dtos.StatusResponse;
import com.api.scholarships.entities.Status;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.services.interfaces.StatusService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

  @MockBean
  private StatusService statusService;
  @MockBean
  private StatusMapper statusMapper;
  @Autowired
  private MockMvc client;
  private String url="/api/v1/scholarships/statuses";
  private StatusDTO statusDTO;

  @BeforeEach
  void setUp(){
    statusDTO=StatusDTO.builder()
        .id(1L)
        .statusName("CERRADO")
        .build();
  }


  @Test
  @DisplayName("Test StatusController, test to find a status by id")
  void getStatusById() throws Exception {
    //given
    Status statusToCreate=Status.builder()
        .id(1L)
        .statusName("CERRADO")
        .build();
    given(statusService.findId(anyLong())).willReturn(statusToCreate);
    given(statusMapper.statusToStatusDTO(any(Status.class))).willReturn(statusDTO);
    //when
    ResultActions response=client.perform(get(url+"/"+statusToCreate.getId())
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(statusToCreate.getId()))
        .andExpect(jsonPath("$.statusName").value(statusToCreate.getStatusName()));
  }

  @Test
  @DisplayName("Test StatusController, test to find all statuses")
  void getAllStatutes() throws Exception {
    //given
    StatusResponse statusResponse=StatusResponse.builder()
        .content(List.of(statusDTO))
        .numberPage(0)
        .sizePage(10)
        .lastOne(true)
        .totalPages(1)
        .totalElements(1L)
        .build();
    given(statusService.findAll(anyInt(), anyInt(), anyString(), anyString())).willReturn(statusResponse);
    //when
    ResultActions response=client.perform(get(url).contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.content.size()").value(1))
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content").isNotEmpty())
        .andExpect(jsonPath("$.content.size()").value(1))
        .andExpect(jsonPath("$.numberPage").value(0))
        .andExpect(jsonPath("$.sizePage").value(10))
        .andExpect(jsonPath("$.lastOne").value(true))
        .andExpect(jsonPath("$.totalPages").value(1))
        .andExpect(jsonPath("$.totalElements").value(1));
  }
}
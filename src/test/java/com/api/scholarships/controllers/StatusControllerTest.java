package com.api.scholarships.controllers;

import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.services.interfaces.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
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


}
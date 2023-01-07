package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.services.interfaces.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
  @MockBean
  private CompanyService companyService;
  @MockBean
  private CompanyMapper companyMapper;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  private String url = "/api/v1/scholarships/companies";
  private Company company;
  private User user;
  private Image image;
  private CompanyDTOResponse companyDTOResponse;

  @BeforeEach
  void init(){
    user = User.builder()
        .name("Juanito")
        .surname("Perez")
        .password("123456")
        .dni("5865486758697")
        .email("email@emial.com")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    image = Image.builder()
        .id(1L)
        .name("image")
        .imageId("imageId")
        .url("url")
        .build();

    company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(List.of(user))
        .image(image)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    companyDTOResponse = CompanyDTOResponse.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(List.of(user))
        .image(image)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  @Test
  @DisplayName("Test CompanyController, test to get a company by id")
  void getOne() throws Exception {
    //given
    given(companyService.getOne(any(Long.class))).willReturn(company);
    given(companyMapper.companyToCompanyDTOResponse(any(Company.class))).willReturn(companyDTOResponse);
    //when
    ResultActions response=mockMvc.perform(get(url+"/{id}",1L));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(companyDTOResponse.getId()))
        .andExpect(jsonPath("$.name").value(companyDTOResponse.getName()))
        .andExpect(jsonPath("$.address").value(companyDTOResponse.getAddress()))
        .andExpect(jsonPath("$.phone").value(companyDTOResponse.getPhone()))
        .andExpect(jsonPath("$.email").value(companyDTOResponse.getEmail()))
        .andExpect(jsonPath("$.image.id").value(companyDTOResponse.getImage().getId()))
        .andExpect(jsonPath("$.image.name").value(companyDTOResponse.getImage().getName()))
        .andExpect(jsonPath("$.image.imageId").value(companyDTOResponse.getImage().getImageId()))
        .andExpect(jsonPath("$.image.url").value(companyDTOResponse.getImage().getUrl()))
        .andExpect(jsonPath("$.createdAt").value(companyDTOResponse.getCreatedAt().toString()))
        .andExpect(jsonPath("$.updatedAt").value(companyDTOResponse.getUpdatedAt().toString()));
  }
}
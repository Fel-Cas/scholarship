package com.api.scholarships.controllers;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.services.interfaces.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
  private CompanyDTOResponse companyDTO;


}
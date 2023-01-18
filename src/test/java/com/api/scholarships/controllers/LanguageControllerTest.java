package com.api.scholarships.controllers;

import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.services.interfaces.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class LanguageControllerTest {

  @MockBean
  private LanguageService languageService;
  @MockBean
  private LanguageMapper languageMapper;
  @Autowired
  private MockMvc client;
  private String url="/api/v1/scholarships/languages";
  private LanguageDTO languageDTO;

  @BeforeEach
  void setUp() {
    languageDTO=LanguageDTO.builder()
        .id(3L)
        .languageName("FRANCÉS")
        .build();
  }

}
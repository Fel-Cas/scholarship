package com.api.scholarships.controllers;

import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.dtos.LanguageResponse;
import com.api.scholarships.entities.Language;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.services.interfaces.LanguageService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)
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

  @Test
  @DisplayName("Test LanguageController, test to find a Language by id")
  void testFindByID() throws Exception {
    //given
    Language language=new Language(3L, "FRANCÉS");
    given(languageService.findById(3L)).willReturn(language);
    given(languageMapper.languageToLaguageDTO(language)).willReturn(languageDTO);
    //when
    ResultActions response=client.perform(get(url+"/{id}", 3L)
        .contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.id").value(language.getId()))
        .andExpect(jsonPath("$.languageName").value(language.getLanguageName()));
  }

  @Test
  @DisplayName("Test LanguageController, test to find all Languages")
  void testFindAll() throws Exception {
    //given
    LanguageResponse languageResponse=LanguageResponse.builder()
        .content(List.of(languageDTO))
        .numberPage(0)
        .sizePage(10)
        .lastOne(true)
        .totalPages(1)
        .totalElements(1L)
        .build();
    given(languageService.findAll(anyInt(), anyInt(), anyString(), anyString())).willReturn(languageResponse);
    //when
    ResultActions response=client.perform(get(url).contentType(MediaType.APPLICATION_JSON));
    //then
    response.andExpect(status().isOk())
        .andDo(print())
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
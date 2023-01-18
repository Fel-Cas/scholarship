package com.api.scholarships.services;

import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.entities.Language;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.repositories.LanguageRepository;
import com.api.scholarships.services.implementation.LanguageServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LanguageServiceTest {
  @Mock
  private LanguageRepository languageRepository;
  @Mock
  private LanguageMapper languageMapper;
  @InjectMocks
  private LanguageServiceImp languageService;
  private Language language;
  private LanguageDTO languageDTO;

  @BeforeEach
  void setUp(){
    language=Language.builder()
        .id(4L)
        .languageName("INGLÉS")
        .build();
    languageDTO=LanguageDTO.builder()
        .id(4L)
        .languageName("INGLÉS")
        .build();
  }

  @Test
  @DisplayName("Test LanguageService, test to find a language by id")
  void testGetLanguageById(){
    //given
    given(languageRepository.findById(anyLong())).willReturn(Optional.of(language));
    //when
    Language languageFound = languageService.findById(anyLong());
    //then
    assertAll(
        ()->assertNotNull(languageFound),
        ()->assertEquals(language.getId(),languageFound.getId()),
        ()->assertEquals(language.getLanguageName(),languageFound.getLanguageName())
    );
  }
}
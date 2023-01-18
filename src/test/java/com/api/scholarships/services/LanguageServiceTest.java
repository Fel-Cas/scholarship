package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.entities.Language;
import com.api.scholarships.exceptions.NotFoundException;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        ()->assertEquals(language.getLanguageName(),languageFound.getLanguageName()),
        ()->verify(languageRepository,times(1)).findById(anyLong())
    );
  }

  @Test
  @DisplayName("Test LanguageService, test to check an error when users try to search for a language by id and it doesn't exist")
  void  testGetLanguageByIdNotFound(){
    //given
    given(languageRepository.findById(10L)).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->languageService.findById(10L));
    //then
    assertEquals(Messages.MESSAGE_LANGUAGE_NOT_FOUND.formatted(10L),exception.getMessage());
  }
}
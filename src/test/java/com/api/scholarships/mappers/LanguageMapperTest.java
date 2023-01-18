package com.api.scholarships.mappers;

import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.entities.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LanguageMapperTest {

  private LanguageMapper languageMapper=Mappers.getMapper(LanguageMapper.class);
  private Language language;
  @BeforeEach
  void setUp(){
    language=Language.builder()
        .id(6L)
        .languageName("JAPONÉS")
        .build();
  }

  @Test
  @DisplayName("Test LanguageMapper, test to pass from language class to LanguageDTO class")
  void languageToLanguageDTO(){
    //given
    //when
    LanguageDTO languageDTO=languageMapper.languageToLaguageDTO(language);
    //then
    assertAll(
        ()->assertNotNull(languageDTO),
        ()->assertEquals(language.getId(), languageDTO.getId()),
        ()->assertEquals(language.getLanguageName(), languageDTO.getLanguageName())
    );
  }

  @Test
  @DisplayName("Test LanguageMapper, test to switch from the list of language items to the list of languageDTO items")
  void listLanguageToListLanguageDTO(){
    //given
    List<Language> languages=List.of(language);
    //when
    List<LanguageDTO> languageDTOS=languageMapper.languageToLaguageDTO(languages);
    //then
    assertAll(
        ()->assertNotNull(languageDTOS),
        ()->assertEquals(languages.size(),languageDTOS.size()),
        ()->assertEquals(languages.get(0).getId(),languageDTOS.get(0).getId()),
        ()->assertEquals(languages.get(0).getLanguageName(),languageDTOS.get(0).getLanguageName())
    );
  }

}
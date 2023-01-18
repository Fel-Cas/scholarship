package com.api.scholarships.mappers;

import com.api.scholarships.entities.Language;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;

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

}
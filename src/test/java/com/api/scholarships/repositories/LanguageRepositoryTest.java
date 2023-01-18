package com.api.scholarships.repositories;

import com.api.scholarships.entities.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class LanguageRepositoryTest {
  @Autowired
  private LanguageRepository languageRepository;

  @Test
  @DisplayName("Test LanguageRepository, test to create a language")
  void testCreateLanguage(){
    //given
    Language language= Language.builder()
        .languageName("ÁRABE")
        .build();
    //when
    Language languageSaved=languageRepository.save(language);
    //then
    assertAll(
        ()-> assertNotNull(languageSaved),
        ()-> assertEquals(languageSaved.getLanguageName(),language.getLanguageName()),
        ()->assertThat(languageSaved.getId()).isGreaterThan(7)
    );
  }

}
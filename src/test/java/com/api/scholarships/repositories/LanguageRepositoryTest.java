package com.api.scholarships.repositories;

import com.api.scholarships.entities.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

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

  @Test
  @DisplayName("Test LanguageRepository, test to find language by id")
  void testFindLanguageById(){
    //given
    //when
    Optional<Language> languageFound=languageRepository.findById(3L);
    //then
    assertAll(
        ()->assertNotNull(languageFound),
        ()->assertTrue(languageFound.isPresent()),
        ()->assertEquals(languageFound.get().getLanguageName(), "FRANCÉS"),
        ()->assertThat(languageFound.get().getId()).isEqualTo(3));
  }

  @Test
  @DisplayName("Test LanguageRepository, test to find a language by name")
  void testFindLanguageByName(){
    //given
    //when
    Optional<Language> languageFound=languageRepository.findByLanguageName("ITALIANO");
    //then
    assertAll(
        ()->assertNotNull(languageFound),
        ()->assertTrue(languageFound.isPresent()),
        ()->assertEquals(languageFound.get().getLanguageName(), "ITALIANO"),
        ()->assertThat(languageFound.get().getId()).isEqualTo(5));
  }

  @Test
  @DisplayName("Test LanguageRepository, test to find all languages")
  void testFindAllLanguages(){
    //given
    //when
    List<Language> languagesFound=languageRepository.findAll();
    //then
    assertAll(
        ()->assertNotNull(languagesFound),
        ()->assertThat(languagesFound.size()).isGreaterThan(6));
  }

  @Test
  @DisplayName("Test LanguageRepository, test to delete a language")
  void testDeleteLanguage(){
    //given
    //when
    languageRepository.deleteById(6L);
    //then
    assertTrue(languageRepository.findById(6L).isEmpty());
    assertThat(languageRepository.findAll().size()).isGreaterThan(5);
  }
}
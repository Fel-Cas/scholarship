package com.api.scholarships.repositories;

import com.api.scholarships.entities.LegalRepresentative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LegalRepresentativeRepositoryTest {

  @Autowired
  private LegalRepresentativeRepository legalRepresentativeRepository;
  private LegalRepresentative legalRepresentative;

  @BeforeEach
  void init(){
    legalRepresentative= LegalRepresentative.builder()
      .name("Andrés Felipe")
      .surname("Castro Monsalve")
      .dni("1001233147")
      .email("andres.c@gmail.com")
      .password("123456")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test save legal representative")
  void testSaveLegalRepresentative(){
    //given
    //when
    LegalRepresentative legalRepresentativeSaved = legalRepresentativeRepository.save(legalRepresentative);
    //then
    assertNotNull(legalRepresentativeSaved);
    assertThat(legalRepresentativeSaved.getId()).isGreaterThan(0);
    assertNotNull(legalRepresentativeSaved.getCreatedAt());
    assertNotNull(legalRepresentativeSaved.getUpdatedAt());
  }
}
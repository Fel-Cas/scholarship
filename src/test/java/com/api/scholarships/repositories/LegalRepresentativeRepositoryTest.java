package com.api.scholarships.repositories;

import com.api.scholarships.entities.LegalRepresentative;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
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
      .build();
  }
}
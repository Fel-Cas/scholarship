package com.api.scholarships.repositories;

import com.api.scholarships.entities.Career;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CareerRepositoryTest {

  @Autowired
  private  CareerRepository careerRepository;
  private Career career;

  @BeforeEach
  void setup(){
    career=Career.builder()
        .careerName("ENFERMERIA")
        .build();
  }
}
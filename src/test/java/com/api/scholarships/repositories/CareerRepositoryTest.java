package com.api.scholarships.repositories;

import com.api.scholarships.entities.Career;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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

  @Test
  @DisplayName("Test CareerRepository, test to create a new career")
  void testCreateCareer(){
    //given
    //when
    Career careerSaved=careerRepository.save(career);
    //when
    assertAll(
        ()->assertNotNull(careerSaved),
        ()->assertThat(careerSaved.getId()).isGreaterThan(0),
        ()->assertEquals("ENFERMERIA", careerSaved.getCareerName())
    );
  }

  @Test
  @DisplayName("Test CareerRepository, test to find a career by id")
  void testFindById(){
    //given
    career.setCareerName("MÚSICA");
    Career careerSaved=careerRepository.save(career);
    //when
    Optional<Career> careerFound=careerRepository.findById(careerSaved.getId());
    //then
    assertAll(
        ()->assertNotNull(careerFound),
        ()->assertTrue(careerFound.isPresent()),
        ()->assertEquals(careerSaved.getId(), careerFound.get().getId()),
        ()->assertEquals(careerSaved.getCareerName(), careerFound.get().getCareerName())
    );
  }

  @Test
  @DisplayName("Test CareerRepository, test to find a career by name")
  void testFindByName(){
    //given
    career.setCareerName("INGENIERIA MECANICA");
    Career careerSaved=careerRepository.save(career);
    //when
    Optional<Career> careerFound=careerRepository.findByCareerName(careerSaved.getCareerName());
    //then
    assertAll(
        ()->assertNotNull(careerFound),
        ()->assertTrue(careerFound.isPresent()),
        ()->assertEquals(careerSaved.getId(), careerFound.get().getId()),
        ()->assertEquals(careerSaved.getCareerName(), careerFound.get().getCareerName())
    );
  }
}
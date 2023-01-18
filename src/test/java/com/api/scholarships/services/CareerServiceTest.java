package com.api.scholarships.services;

import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.repositories.CareerRepository;
import com.api.scholarships.services.implementation.CareerServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CareerServiceTest {
  @Mock
  private CareerRepository careerRepository;
  @Mock
  private CareerMapper careerMapper;
  @InjectMocks
  private CareerServiceImp careerService;
  private Career career;

  @BeforeEach()
  void setUp(){
    career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
  }

  @Test
  @DisplayName("Test CareerService, test to save a career")
  void testToSaveCareer() {
    //given
    CareerDTO careerDTO=CareerDTO.builder().careerName("INGENIERIA DE SISTEMAS").build();
    given(careerRepository.existsByCareerName(anyString())).willReturn(false);
    given(careerRepository.save(any(Career.class))).willReturn(career);
    //when
    Career careerSaved=careerService.create(careerDTO);
    //then
    assertAll(
        ()->assertNotNull(careerSaved),
        ()->assertEquals(career.getId(),careerSaved.getId()),
        ()->assertEquals(career.getCareerName(),careerSaved.getCareerName())
    );
  }

}
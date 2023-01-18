package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.entities.Career;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

  @Test
  @DisplayName("Test CareerService, test to check for an exception when trying  to save a career with an incorrect name")
  void testToCheckExceptionCreateCareer(){
    //given
    CareerDTO careerDTO=CareerDTO.builder().careerName("CANTANTE").build();
    given(careerRepository.existsByCareerName(careerDTO.getCareerName())).willReturn(true);
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->careerService.create(careerDTO));
    //then
    assertEquals(Messages.MESSAGE_CREATE_CAREER_WITH_WRONG_NAME.formatted(careerDTO.getCareerName()),exception.getMessage());
  }

  @Test
  @DisplayName("Test CareerService, test to find a career by id")
  void testToFindCareerById(){
    //given
    given(careerRepository.findById(anyLong())).willReturn(Optional.of(career));
    //when
    Career careerFound=careerService.findById(anyLong());
    //then
    assertAll(
        ()->assertNotNull(careerFound),
        ()->assertEquals(career.getId(),careerFound.getId()),
        ()->assertEquals(career.getCareerName(),careerFound.getCareerName())
    );
  }

  @Test
  @DisplayName("Test CareerService, test to check an exception  when trying to find a career by id and doesn't exist")
  void testToCheckExceptionFindById(){
    //given
    given(careerRepository.findById(career.getId())).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->careerService.findById(career.getId()));
    //then
    assertEquals(Messages.MESSAGE_CAREER_NOT_FOUND.formatted(career.getId()),exception.getMessage());
  }

  @Test
  @DisplayName("Test CareerService, test to find a career by name")
  void  testToFindCareerByName(){
    //given
    given(careerRepository.findByCareerName(anyString())).willReturn(Optional.of(career));
    //when
    Career careerFound=careerService.findByName(anyString());
    //then
    assertAll(
        ()->assertNotNull(careerFound),
        ()->assertEquals(career.getId(),careerFound.getId()),
        ()->assertEquals(career.getCareerName(),careerFound.getCareerName())
    );
  }

  @Test
  @DisplayName("Test CareerService, test to check an exception  when trying to find a career by name and doesn't exist")
  void testToCheckExceptionFindByName(){
    //given
    given(careerRepository.findByCareerName(career.getCareerName())).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->careerService.findByName(career.getCareerName()));
    //then
    assertEquals(Messages.MESSAGE_CAREER_NOT_FOUND_BY_NAME.formatted(career.getCareerName()),exception.getMessage());
  }
}
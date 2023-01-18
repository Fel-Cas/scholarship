package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.entities.Career;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CareerMapperTest {
  private CareerMapper careerMapper= Mappers.getMapper(CareerMapper.class);
  private Career career;

  @BeforeEach
  void setUp(){
    career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
  }

  @Test
  @DisplayName("Test CareerMapper, test to pass from Career class to CareerDTOResponse class")
  void testCareerToCareerDTOResponse(){
    //given
    //when
    CareerDTOResponse careerDTOResponse=careerMapper.careerToCareerDTOResponse(career);
    //then
    assertAll(
        ()->assertNotNull(careerDTOResponse),
        ()->assertEquals(career.getId(),careerDTOResponse.getId()),
        ()->assertEquals(career.getCareerName(),careerDTOResponse.getCareerName())
    );
  }

}
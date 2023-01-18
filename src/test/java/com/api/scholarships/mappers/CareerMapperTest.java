package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

  @Test
  @DisplayName("Test CareerMapper, test to pass from list of Career items  to list CareerDTOResponse itmes")
  void testListOfCareerToCareerDTOResponse(){
    //given
    List<Career> careers=List.of(career);
    //when
    List<CareerDTOResponse> careerDTOResponseList=careerMapper.careerListToCareerDTOResponseList(careers);
    //then
    assertAll(
        ()->assertNotNull(careerDTOResponseList),
        ()->assertEquals(careers.size(),careerDTOResponseList.size()),
        ()->assertEquals(careers.get(0).getId(),careerDTOResponseList.get(0).getId()),
        ()->assertEquals(careers.get(0).getCareerName(),careerDTOResponseList.get(0).getCareerName())
    );
  }

}
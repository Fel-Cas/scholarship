package com.api.scholarships.mappers;

import com.api.scholarships.entities.Career;
import org.junit.jupiter.api.BeforeEach;
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

}
package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.entities.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CountryMapperTest {

  private CountryMapper countryMapper= Mappers.getMapper(CountryMapper.class);

  @Test
  @DisplayName("Test CountryMapper, test to pass from country to countryDTOResponse")
  void testToCountryDTOResponse() {
    //given
    Country country=Country.builder()
        .id(1L)
        .countryName("BRASIL")
        .abbreviation("BRA")
        .build();
    //when
    CountryDTOResponse countryDTOResponse=countryMapper.countryToCountryDTOResponse(country);
    //then
    assertAll(
        ()->assertEquals(country.getId(),countryDTOResponse.getId()),
        ()->assertEquals(country.getCountryName(),countryDTOResponse.getCountryName()),
        ()->assertEquals(country.getAbbreviation(),countryDTOResponse.getAbbreviation()));
  }

}
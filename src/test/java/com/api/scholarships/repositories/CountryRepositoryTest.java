package com.api.scholarships.repositories;

import com.api.scholarships.entities.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class CountryRepositoryTest {

  @Autowired
  private  CountryRepository countryRepository;
  private Country country;
  @BeforeEach
  void setUp() {
    country=Country
        .builder()
        .countryName("Nigeria")
        .abbreviation("NG")
        .build();
  }

  @Test
  @DisplayName("Test CountryRepository, test to create a country")
  void testCreate(){
    //given
    //when
    Country countrySaved=countryRepository.save(country);
    //then
    assertEquals(1L, countrySaved.getId());
    assertEquals(country.getCountryName(), countrySaved.getCountryName());
    assertEquals(country.getAbbreviation(), countrySaved.getAbbreviation());
  }
}
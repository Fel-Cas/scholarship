package com.api.scholarships.repositories;

import com.api.scholarships.entities.Country;
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
    assertThat(countrySaved.getId()).isGreaterThan(0);
    assertEquals(country.getCountryName(), countrySaved.getCountryName());
    assertEquals(country.getAbbreviation(), countrySaved.getAbbreviation());
  }

  @Test
  @DisplayName("Test CountryRepository, test to find a country by its id")
  void testFindById(){
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    Optional<Country> countryFound=countryRepository.findById(countrySaved.getId());
    //then
    assertTrue(countryFound.isPresent());
    assertEquals(countrySaved.getId(),countryFound.get().getId());
    assertEquals(countrySaved.getCountryName(),countryFound.get().getCountryName());
    assertEquals(countrySaved.getAbbreviation(),countryFound.get().getAbbreviation());
  }

  @Test
  @DisplayName("Test CountryRepository, test to determinate if a country exists by name and abbreviation")
  void testExistsByNameAndAbbreviation(){
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    boolean exist=countryRepository.existsByCountryNameAndAbbreviation(country.getCountryName(), country.getAbbreviation());
    boolean noExist=countryRepository.existsByCountryNameAndAbbreviation("xxx", country.getAbbreviation());
    //then
    assertTrue(exist);
    assertFalse(noExist);
  }
}
package com.api.scholarships.repositories;

import com.api.scholarships.entities.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
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

  @Test
  @DisplayName("Test CountryRepository, test to determinate if a country exists by name")
  void testExistsByName() {
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    boolean exist=countryRepository.existsByCountryName(country.getCountryName());
    boolean noExist=countryRepository.existsByCountryName("xxx");
    //then
    assertTrue(exist);
    assertFalse(noExist);
  }

  @Test
  @DisplayName("Test CountryRepository, test to determinate if a country exists by abbreviation")
  void testExistsByAbbreviation() {
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    boolean exist=countryRepository.existsByAbbreviation(country.getAbbreviation());
    boolean noExist=countryRepository.existsByAbbreviation("xxx");
    //then
    assertTrue(exist);
    assertFalse(noExist);
  }

  @Test
  @DisplayName("Test CountryRepository, test to find a  Country by name")
  void findByCountryName() {
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    Optional<Country> countryFound=countryRepository.findByCountryName(country.getCountryName());
    //then
    assertTrue(countryFound.isPresent());
    assertEquals(countrySaved.getId(),countryFound.get().getId());
    assertEquals(countrySaved.getCountryName(),countryFound.get().getCountryName());
    assertEquals(countrySaved.getAbbreviation(),countryFound.get().getAbbreviation());
  }

  @Test
  @DisplayName("Test CountryRepository, test to find all countries")
  void testFindAll(){
    //given
    countryRepository.save(country);
    //when
    List<Country> countries=countryRepository.findAll();
    //then
    assertNotNull(countries);
    assertThat(countries.size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test CountryRepository, test to delete a country")
  void testDelete(){
    //given
    Country countrySaved=countryRepository.save(country);
    //when
    countryRepository.delete(countrySaved);
    //then
    assertThat(countryRepository.findById(countrySaved.getId())).isEmpty();
  }
}
package com.api.scholarships.services;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.repositories.CountryRepository;
import com.api.scholarships.services.implementation.CountryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CountryServiceTest {

  @Mock
  private CountryRepository countryRepository;
  @Mock
  private CountryMapper countryMapper;
  @InjectMocks
  private CountryServiceImp countryService;
  private Country country;

  @BeforeEach
  void setUp(){
    country=Country
        .builder()
        .id(1L)
        .countryName("BRASIL")
        .abbreviation("BRA")
        .build();
  }

  @Test
  @DisplayName("Test CountryService, test to create a country")
  void testCreate(){
    //given
    given(countryRepository.existsByCountryNameAndAbbreviation(anyString(), anyString())).willReturn(false);
    given(countryRepository.existsByCountryName(anyString())).willReturn(false);
    given(countryRepository.existsByAbbreviation(anyString())).willReturn(false);
    given(countryRepository.save(any(Country.class))).willReturn(country);

    CountryDTO countryDTO= CountryDTO.builder()
        .countryName("Brasil")
        .abbreviation("Bra")
        .build();
    //when
    Country countryCreated=countryService.create(countryDTO);
    //then
    assertNotNull(countryCreated);
    assertEquals(1L, countryCreated.getId());
    assertEquals(countryDTO.getCountryName().toUpperCase(), countryCreated.getCountryName());
    assertEquals(countryDTO.getAbbreviation().toUpperCase(), countryCreated.getAbbreviation());
  }
}
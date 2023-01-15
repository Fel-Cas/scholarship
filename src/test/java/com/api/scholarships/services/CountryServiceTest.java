package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

  @Test
  @DisplayName("Test CountryService, test to check the error when users try  to create a country with a name and abbreviation already saved")
  void testCreateDuplicateCountry(){
    //given
    given(countryRepository.existsByCountryNameAndAbbreviation(anyString(), anyString())).willReturn(true);
    CountryDTO countryDTO= CountryDTO.builder()
        .countryName("Brasil")
        .abbreviation("Bra")
        .build();
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->countryService.create(countryDTO));
    //then
    assertEquals(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME_AND_ABBREVIATION.formatted(countryDTO.getCountryName(), countryDTO.getAbbreviation()),
      exception.getMessage());
  }

  @Test
  @DisplayName("Test CountryService, test to check the error when users try to create a country with an already saved name")
  void testCreateCountryWithWrongName(){
    //given
    given(countryRepository.existsByCountryNameAndAbbreviation(anyString(), anyString())).willReturn(false);
    given(countryRepository.existsByCountryName(anyString())).willReturn(true);
    CountryDTO countryDTO= CountryDTO.builder()
        .countryName("Brasil")
        .abbreviation("Bra")
        .build();
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->countryService.create(countryDTO));
    //then
    assertEquals(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME.formatted(countryDTO.getCountryName()),exception.getMessage());
  }

  @Test
  @DisplayName("Test CountryService, test to check the error when users try to create a country with an already saved abbreviation")
  void testCreateCountryWithWrongAbbreviation(){
    //given
    given(countryRepository.existsByCountryNameAndAbbreviation(anyString(), anyString())).willReturn(false);
    given(countryRepository.existsByCountryName(anyString())).willReturn(false);
    given(countryRepository.existsByAbbreviation(anyString())).willReturn(true);
    CountryDTO countryDTO= CountryDTO.builder()
        .countryName("Brasil")
        .abbreviation("Bra")
        .build();
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->countryService.create(countryDTO));
    //then
    assertEquals(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_ABBREVIATION.formatted(countryDTO.getAbbreviation()),exception.getMessage());
  }

  @Test
  @DisplayName("Test CountryService, test to find a country by id")
  void testFindById(){
    //given
    given(countryRepository.findById(anyLong())).willReturn(Optional.ofNullable(country));
    //when
    Country countryFound=countryService.findById(anyLong());
    //then
    assertNotNull(countryFound);
    assertEquals(1L,countryFound.getId());
    assertEquals(country.getCountryName(),countryFound.getCountryName());
    assertEquals(country.getAbbreviation(),countryFound.getAbbreviation());
  }

  @Test
  @DisplayName("Test CountryService, test to check error when trying to search for a country that doesn't exist")
  void testFindByIdNotExist(){
    //given
    given(countryRepository.findById(1L)).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->countryService.findById(1L));
    //then
    assertEquals(Messages.MESSAGE_COUNTRY_NOT_FOUND.formatted(1L),exception.getMessage());
  }

  @Test
  @DisplayName("Test CountryService, test to find a country by name")
  void testFindByName(){
    //given
    given(countryRepository.findByCountryName(country.getCountryName())).willReturn(Optional.ofNullable(country));
    //when
    Country countryFound=countryService.findByName(country.getCountryName());
    //then
    assertNotNull(countryFound);
    assertEquals(1L,countryFound.getId());
    assertEquals(country.getCountryName(),countryFound.getCountryName());
    assertEquals(country.getAbbreviation(),countryFound.getAbbreviation());
  }

  @Test
  @DisplayName("Test CountryService, test to check error when trying to search for a country that doesn't exist")
  void  testFindByNameNotExist(){
    //given
    given(countryRepository.findByCountryName("Argentina")).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->countryService.findByName("Argentina"));
    //then
    assertEquals(Messages.MESSAGE_COUNTRY_NOT_FOUND_BY_NAME.formatted("Argentina"),exception.getMessage());
  }

  @Test
  @DisplayName("Test CountryService, test to find all countries")
  void testFindAll(){
    //given
    CountryDTOResponse countryDTOResponse=CountryDTOResponse.builder()
        .id(1L)
        .countryName("BRASIL")
        .abbreviation("BRA")
        .build();

    Page<Country> countries=new PageImpl<>(List.of(country));
    given(countryRepository.findAll(any(Pageable.class))).willReturn(countries);
    given(countryMapper.countryToCountryResponse(List.of(country))).willReturn(List.of(countryDTOResponse));
    //when
    CountryResponse countriesFound=countryService.findAll(0,1,"id","ASC");
    //then
    assertAll(
        ()->assertNotNull(countriesFound),
        ()->assertThat(countriesFound.getContent().size()).isGreaterThan(0),
        ()->assertEquals(countriesFound.getTotalPages(),1),
        ()->assertEquals(countriesFound.getTotalElements(),1),
        ()->assertEquals(countriesFound.getSizePage(),1),
        ()->assertEquals(countriesFound.getNumberPage(),0),
        ()->assertTrue(countriesFound.isLastOne()));
  }


}
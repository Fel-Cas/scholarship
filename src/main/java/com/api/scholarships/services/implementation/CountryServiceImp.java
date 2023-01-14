package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.repositories.CountryRepository;
import com.api.scholarships.services.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImp implements CountryService {

  @Autowired
  private CountryRepository countryRepository;
  @Autowired
  private CountryMapper countryMapper;

  @Override
  public Country create(CountryDTO countryDTO) {
    validateInformationCreateCountry(countryDTO.getCountryName(), countryDTO.getAbbreviation());
    return countryRepository.save(Country
        .builder()
        .countryName(countryDTO.getCountryName().toUpperCase())
        .abbreviation(countryDTO.getAbbreviation().toUpperCase())
        .build()
    );
  }

  @Override
  public Country findById(Long id) {
    Optional<Country> countryFound=countryRepository.findById(id);
    if(countryFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_COUNTRY_NOT_FOUND.formatted(id));
    }
    return countryFound.get();
  }

  @Override
  public Country findByName(String name) {
    Optional<Country> countryFound=countryRepository.findByCountryName(name);
    if(countryFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_COUNTRY_NOT_FOUND_BY_NAME.formatted(name));
    }
    return countryFound.get();
  }

  @Override
  public CountryResponse findAll(int page, int size, String sort, String order) {
    Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sort).ascending() : Sort.by(sort).descending();
    Page<Country> countriesFound=countryRepository.findAll(PageRequest.of(page,size,sortDirection));
    return CountryResponse.builder()
        .content(countryMapper.countryToCountryResponse(countriesFound.getContent()))
        .totalPages(countriesFound.getTotalPages())
        .totalElements(countriesFound.getTotalElements())
        .lastOne(countriesFound.isLast())
        .numberPage(countriesFound.getNumber())
        .sizePage(countriesFound.getSize())
        .build();
  }

  @Override
  public void delete(Long id) {
    Country countryFound=findById(id);
    countryRepository.delete(countryFound);
  }

  protected void validateInformationCreateCountry(String countryName, String abbreviation){

    if(countryRepository.existsByCountryNameAndAbbreviation(countryName, abbreviation)){
      throw  new BadRequestException(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME_AND_ABBREVIATION.formatted(countryName, abbreviation));
    }
    if(countryRepository.existsByCountryName(countryName)){
      throw new BadRequestException(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME.formatted(countryName));
    }
    if (countryRepository.existsByAbbreviation(abbreviation)){
      throw new BadRequestException(Messages.MESSAGE_CREATE_COUNTRY_WITH_WRONG_ABBREVIATION.formatted(abbreviation));
    }
  }
}

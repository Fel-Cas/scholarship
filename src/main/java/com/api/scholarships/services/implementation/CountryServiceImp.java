package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.repositories.CountryRepository;
import com.api.scholarships.services.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImp implements CountryService {

  @Autowired
  private CountryRepository countryRepository;

  @Override
  public Country create(CountryDTO countryDTO) {
    return null;
  }

  @Override
  public Country findById(Long id) {
    return null;
  }

  @Override
  public Country findByName(String name) {
    return null;
  }

  @Override
  public CountryResponse findAll() {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}

package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;

public interface CountryService {
  public Country create(CountryDTO countryDTO);
  public Country findById(Long id);
  public Country findByName(String name);
  public CountryResponse findAll();
  public void delete(Long id);
}

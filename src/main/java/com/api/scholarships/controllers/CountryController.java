package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.services.interfaces.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.COUNTRIES)
public class CountryController {

  @Autowired
  private CountryService countryService;
  @Autowired
  private CountryMapper countryMapper;

  @PostMapping()
  public ResponseEntity<CountryDTOResponse> create(@Valid @RequestBody CountryDTO countryDTO){
    Country countrySaved=countryService.create(countryDTO);
    return new ResponseEntity(countryMapper.countryToCountryDTOResponse(countrySaved), HttpStatus.CREATED);
  }
}

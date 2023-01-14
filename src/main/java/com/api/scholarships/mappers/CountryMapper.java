package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.entities.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
  public CountryDTOResponse countryToCountryDTOResponse(Country country);
  public List<CountryDTOResponse> countryToCountryResponse(Iterable<Country> countries);

}

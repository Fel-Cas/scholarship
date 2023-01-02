package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.entities.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
  CompanyDTOResponse companyToCompanyDTOResponse(Company company);
  List<CompanyDTOResponse> companyToCompanyDTOResponse(Iterable<Company> company);
}

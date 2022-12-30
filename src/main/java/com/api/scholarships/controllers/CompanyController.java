package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.entities.Company;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.services.interfaces.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(Endpoints.COMPANIES)
public class CompanyController {

  @Autowired
  private CompanyService companyService;
  @Autowired
  private CompanyMapper companyMapper;

  @PostMapping
  public ResponseEntity<CompanyDTOResponse> create(@ModelAttribute @Valid CompanyDTO companyDTO) throws IOException {
    Company companySaved=companyService.create(companyDTO);
    return new ResponseEntity<>(companyMapper.companyToCompanyDTOResponse(companySaved), HttpStatus.CREATED);
  }

  @GetMapping(Endpoints.ID)
  public ResponseEntity<CompanyDTOResponse> getOne(@PathVariable Long id){
    return ResponseEntity.ok(companyMapper.companyToCompanyDTOResponse(companyService.getOne(id)));
  }
}

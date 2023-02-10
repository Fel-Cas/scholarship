package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.CountryDTO;
import com.api.scholarships.dtos.CountryDTOResponse;
import com.api.scholarships.dtos.CountryResponse;
import com.api.scholarships.entities.Country;
import com.api.scholarships.mappers.CountryMapper;
import com.api.scholarships.services.interfaces.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.COUNTRIES)
public class CountryController {

  @Autowired
  private CountryService countryService;
  @Autowired
  private CountryMapper countryMapper;

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<CountryDTOResponse> create(@Valid @RequestBody CountryDTO countryDTO){
    Country countrySaved=countryService.create(countryDTO);
    return new ResponseEntity(countryMapper.countryToCountryDTOResponse(countrySaved), HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<CountryResponse> getAll(
      @RequestParam(name ="numberPage" ,defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE,required = false) int page,
      @RequestParam(name = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(name = "sort",defaultValue = PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(name = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String order
  ){
    return ResponseEntity.ok(countryService.findAll(page,size,sort,order));
  }
  @GetMapping(Endpoints.ID)
  public ResponseEntity<CountryDTOResponse> get(@PathVariable Long id){
    return ResponseEntity.ok(countryMapper.countryToCountryDTOResponse(countryService.findById(id)));
  }

  @DeleteMapping(Endpoints.ID)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> delete(@PathVariable Long id){
    countryService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

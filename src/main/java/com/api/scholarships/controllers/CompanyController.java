package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.exceptions.BadRequestException;
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

  @GetMapping()
  public ResponseEntity<CompanyResponse> getAll(
      @RequestParam(value ="numberPage" ,defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int page,
      @RequestParam(value ="pagesize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(value ="sort" ,defaultValue =PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(value = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR,required = false) String order
  ){
    return ResponseEntity.ok(companyService.getAll(page, size, sort, order));
  }

  @PutMapping(Endpoints.ID)
  public ResponseEntity<CompanyDTOResponse> update(@PathVariable Long id, @RequestBody @Valid CompanyUpdateDTO companyDTO) throws IOException {
    Company companyUpdated=companyService.update(id, companyDTO);
    return ResponseEntity.ok(companyMapper.companyToCompanyDTOResponse(companyUpdated));
  }

  @DeleteMapping(Endpoints.ID)
  public ResponseEntity<?> delete(@PathVariable Long id){
    companyService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(Endpoints.COMPANIES_USERS)
  public ResponseEntity<CompanyDTOResponse> manageUsersCompany(
      @PathVariable("idCompany") Long idCompany,
      @PathVariable("idUser") Long idUser,
      @RequestParam(value = "action") String action
  ){
    Company companyUpdated=null;
    switch (action.toUpperCase()){
      case "ADD":
        companyUpdated=companyService.addUser(idCompany, idUser);
        break;
      case "REMOVE":
        companyUpdated=companyService.removeUser(idCompany, idUser);
        break;
      default:
        throw new BadRequestException("That action is not valid");
    }
    return ResponseEntity.ok(companyMapper.companyToCompanyDTOResponse(companyUpdated));
  }
}

package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeDTOResponse;
import com.api.scholarships.dtos.LegalRepresentativeResponse;
import com.api.scholarships.entities.LegalRepresentative;
import com.api.scholarships.mappers.LegalRepresentativeMapper;
import com.api.scholarships.services.interfaces.LegalRepresentativeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.LEGAL_REPRESENTATIVE)
public class LegalRepresentativeController {

  @Autowired
  private LegalRepresentativeMapper legalRepresentativeMapper;
  @Autowired
  private LegalRepresentativeService legalRepresentativeService;

  @PostMapping()
  public ResponseEntity<LegalRepresentativeDTOResponse> save(@Valid @RequestBody LegalRepresentativeDTO legalRepresentativeDTO){
    LegalRepresentative legalRepresentative = legalRepresentativeService.save(legalRepresentativeDTO);
    return ResponseEntity.ok(legalRepresentativeMapper.legalRepresentativeToLegalRepresentativeDTOResponse(legalRepresentative));
  }

  @GetMapping()
  public ResponseEntity<LegalRepresentativeResponse> findAll(
      @RequestParam(value ="numberPage" ,defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int page,
      @RequestParam(value ="pagesize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(value ="sort" ,defaultValue =PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
      @RequestParam(value = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR,required = false) String order
  ){
    return ResponseEntity.ok(legalRepresentativeService.getAllLegalRepresentatives(page, size, sort, order));
  }

  @GetMapping(Endpoints.ID)
  public ResponseEntity<LegalRepresentativeDTOResponse> findById(@PathVariable Long id){
    return ResponseEntity.ok(legalRepresentativeMapper.legalRepresentativeToLegalRepresentativeDTOResponse(legalRepresentativeService.getLegalRepresentativeById(id)));
  }
}

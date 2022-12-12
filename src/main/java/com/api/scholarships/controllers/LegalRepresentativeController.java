package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeDTOResponse;
import com.api.scholarships.entities.LegalRepresentative;
import com.api.scholarships.mappers.LegalRepresentativeMapper;
import com.api.scholarships.services.interfaces.LegalRepresentativeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

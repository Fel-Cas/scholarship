package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.services.interfaces.ScholarshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping(Endpoints.SCHOLARSHIPS)
public class ScholarshipController {
  @Autowired
  private ScholarshipService scholarshipService;
  @Autowired
  private ScholarshipMapper scholarshipMapper;

  @PostMapping
  public ResponseEntity<ScholarshipDTOResponse> create(@Valid @ModelAttribute ScholarshipDTO scholarshipDTO) throws IOException, ParseException {
    Scholarship scholarshipSaved=scholarshipService.create(scholarshipDTO);
    return new ResponseEntity<>(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipSaved), HttpStatus.CREATED);
  }
}

package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.services.interfaces.CareerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.CAREERS)
public class CareerController {
  @Autowired
  private CareerService careerService;
  @Autowired
  private CareerMapper careerMapper;

  @PostMapping()
  public ResponseEntity<CareerDTOResponse> create(@Valid @RequestBody CareerDTO careerDTO){
    Career careerSaved=careerService.create(careerDTO);
    return new ResponseEntity<>(careerMapper.careerToCareerDTOResponse(careerSaved), HttpStatus.CREATED);
  }
}

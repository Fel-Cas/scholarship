package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.STATUS)
public class StatusController {
  @Autowired
  private StatusService statusService;
  @Autowired
  private StatusMapper statusMapper;

  @GetMapping()
  public ResponseEntity<?> doAnythig(){
    return ResponseEntity.noContent().build();
  }
}

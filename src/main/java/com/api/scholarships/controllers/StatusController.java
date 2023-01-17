package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.entities.Status;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.STATUS)
public class StatusController {
  @Autowired
  private StatusService statusService;
  @Autowired
  private StatusMapper statusMapper;

  @GetMapping(Endpoints.ID)
  public ResponseEntity<StatusDTO> getOne(@PathVariable("id") Long id){
    Status statusFound=statusService.findId(id);
    return ResponseEntity.ok(statusMapper.statusToStatusDTO(statusFound));
  }
}

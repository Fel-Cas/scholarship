package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.dtos.StatusResponse;
import com.api.scholarships.entities.Status;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping()
  public ResponseEntity<StatusResponse> getAll(
      @RequestParam(name = "numberPage",defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int page,
      @RequestParam(name = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false) int size,
      @RequestParam(name = "sort",defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sort,
      @RequestParam(name = "order",defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String order
  ){
    return ResponseEntity.ok(statusService.findAll(page, size,sort,order));
  }
}

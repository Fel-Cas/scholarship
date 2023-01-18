package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.services.interfaces.CareerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping(Endpoints.ID)
  public ResponseEntity<CareerDTOResponse> getById(@PathVariable("id") Long id){
    Career careerFound=careerService.findById(id);
    return ResponseEntity.ok(careerMapper.careerToCareerDTOResponse(careerFound));
  }

  @GetMapping()
  public ResponseEntity<CareerResponse> findAll(
      @RequestParam(name="numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false ) int page,
      @RequestParam(name="pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false ) int size,
      @RequestParam(name="sort", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sort,
      @RequestParam(name="order", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String order
  ){
    return ResponseEntity.ok(careerService.findAll(page, size, sort, order));
  }

  @DeleteMapping(Endpoints.ID)
  public ResponseEntity<Void> delete(@PathVariable("id") Long id){
    careerService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

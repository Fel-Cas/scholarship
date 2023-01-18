package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.dtos.LanguageResponse;
import com.api.scholarships.entities.Language;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.LANGUAGES)
public class LanguageController {
  @Autowired
  private LanguageService languageService;
  @Autowired
  private LanguageMapper languageMapper;

  @GetMapping(Endpoints.ID)
  public ResponseEntity<LanguageDTO> findById(@PathVariable("id") Long id){
    Language languageFound=languageService.findById(id);
    return ResponseEntity.ok(languageMapper.languageToLaguageDTO(languageFound));
  }

  @GetMapping()
  public ResponseEntity<LanguageResponse> findAll(
      @RequestParam(name="numberPage",defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE,required = false) int page,
      @RequestParam(name="pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int size,
      @RequestParam(name="sort", defaultValue = PaginationRequest.DEFAULT_SORT_BY, required = false) String sort,
      @RequestParam(name="order", defaultValue = PaginationRequest.DEFAULT_SORT_DIR, required = false) String order
  ){
    return ResponseEntity.ok(languageService.findAll(page, size, sort, order));
  }
}

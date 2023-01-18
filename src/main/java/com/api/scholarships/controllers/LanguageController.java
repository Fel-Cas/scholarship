package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.entities.Language;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.LANGUAGES)
public class LanguageController {
  @Autowired
  private LanguageService languageService;
  @Autowired
  private LanguageMapper languageMapper;
}

package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.LanguageResponse;
import com.api.scholarships.entities.Language;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.repositories.LanguageRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImp implements LanguageService {

  @Autowired
  private LanguageRepository languageRepository;
  @Autowired
  private LanguageMapper languageMapper;

  @Override
  public Language findById(Long id) {
    return null;
  }

  @Override
  public Language findByName(String name) {
    return null;
  }

  @Override
  public LanguageResponse findAll(int page, int size, String sort, String order) {
    return null;
  }
}

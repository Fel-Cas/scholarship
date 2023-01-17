package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.LanguageResponse;
import com.api.scholarships.entities.Language;

public interface LanguageService {
  public Language findById(Long id);
  public Language findByName(String name);
  public LanguageResponse findAll(int page, int size, String sort, String order);
}

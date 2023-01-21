package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Language;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipLanguage implements ScholarshipStrategy{

  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private LanguageService languageService;
  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.LANGUAGE;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Language languageFound=languageService.findById(idCondition);
    return scholarshipRepository.findByLanguage(languageFound, pageable);
  }
}

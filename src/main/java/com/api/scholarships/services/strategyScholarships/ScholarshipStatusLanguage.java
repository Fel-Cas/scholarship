package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.constants.Variables;
import com.api.scholarships.entities.Language;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.Status;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScholarshipStatusLanguage implements ScholarshipStrategy {

  @Autowired
  private LanguageService languageService;
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private StatusService statusService;

  @Override
  public ScholarshipType typeStrategy() {
    return ScholarshipType.STATUS_LANGUAGE;
  }

  @Override
  public Page<Scholarship> findScholarshipsByCondition(Pageable pageable, Long idCondition) {
    Status statusFound = statusService.findByName(Variables.STATUS_DEFAULT);
    Language languageFound = languageService.findById(idCondition);
    return scholarshipRepository.findByStatusAndLanguage(statusFound, languageFound, pageable);
  }
}

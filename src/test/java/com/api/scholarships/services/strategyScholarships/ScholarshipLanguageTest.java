package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.Language;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ScholarshipLanguageTest {
  @Mock
  private LanguageService languageService;
  @Mock
  private ScholarshipRepository scholarshipRepository;
  @InjectMocks
  private ScholarshipLanguage scholarshipLanguage;
  private Language language;
  private Scholarship scholarship;
}
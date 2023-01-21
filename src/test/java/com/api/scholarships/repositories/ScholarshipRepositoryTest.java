package com.api.scholarships.repositories;

import com.api.scholarships.entities.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ScholarshipRepositoryTest {
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CareerRepository careerRepository;
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private CourseTypeRepository courseTypeRepository;
  @Autowired
  private ImageRepository imageRepository;
  @Autowired
  private LanguageRepository languageRepository;
  @Autowired
  private StatusRepository statusRepository;
  @Autowired
  private CountryRepository countryRepository;
  private Scholarship scholarship;
}
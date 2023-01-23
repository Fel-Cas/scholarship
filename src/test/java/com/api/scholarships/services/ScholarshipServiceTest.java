package com.api.scholarships.services;

import com.api.scholarships.entities.*;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.implementation.ScholarshipServiceImp;
import com.api.scholarships.services.interfaces.*;
import com.api.scholarships.services.strategyScholarships.ScholarshipContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("Test")
class ScholarshipServiceTest {
  @Mock
  private ScholarshipRepository scholarshipRepository;
  @Mock
  private CareerService careerService;
  @Mock
  private LanguageService languageService;
  @Mock
  private StatusService statusService;
  @Mock
  private CompanyService companyService;
  @Mock
  private CountryService countryService;
  @Mock
  private CourseTypeService courseTypeService;
  @Mock
  private ImageService imageService;
  @Mock
  private ScholarshipMapper scholarshipMapper;
  @Mock
  private ScholarshipContext scholarshipContext;
  @InjectMocks
  private ScholarshipServiceImp scholarshipService;
  private CourseType courseType;
  private Country country;
  private Status status;
  private Language language;
  private Image image;
  private Company company;
  private Career career;
}
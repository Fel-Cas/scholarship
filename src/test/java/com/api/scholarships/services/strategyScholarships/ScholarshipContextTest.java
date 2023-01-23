package com.api.scholarships.services.strategyScholarships;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ScholarshipContextTest {

  private ScholarshipContext scholarshipContext;

  @BeforeEach
  void setUp() throws Exception {
    List<ScholarshipStrategy> strategies= List.of(
        new ScholarshipCareer(),
        new ScholarshipCompany(),
        new ScholarshipCountry(),
        new ScholarshipCourseType(),
        new ScholarshipDefault(),
        new ScholarshipLanguage(),
        new ScholarshipStatus()
        );
    scholarshipContext=new ScholarshipContext(strategies);
    scholarshipContext.afterPropertiesSet();
  }

}
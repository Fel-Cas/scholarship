package com.api.scholarships.services.strategyScholarships;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

  @Test
  @DisplayName("Test ScholarshipContext, test to load default strategy")
  void testLoadDefaultStrategy(){
    ScholarshipStrategy defaultStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.DEFAULT);
    assertNotNull(defaultStrategy);
    assertEquals(defaultStrategy.typeStrategy(),ScholarshipType.DEFAULT);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load career strategy")
  void testLoadCareerStrategy(){
    ScholarshipStrategy careerStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.CAREER);
    assertNotNull(careerStrategy);
    assertEquals(careerStrategy.typeStrategy(),ScholarshipType.CAREER);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load company strategy")
  void testLoadCompanyStrategy(){
    ScholarshipStrategy companyStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.COMPANY);
    assertNotNull(companyStrategy);
    assertEquals(companyStrategy.typeStrategy(),ScholarshipType.COMPANY);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load country strategy")
  void  testLoadCountryStrategy(){
    ScholarshipStrategy countryStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.COUNTRY);
    assertNotNull(countryStrategy);
    assertEquals(countryStrategy.typeStrategy(),ScholarshipType.COUNTRY);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load course type strategy")
  void testLoadCourseTypeStrategy(){
    ScholarshipStrategy courseTypeStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.COURSETYPE);
    assertNotNull(courseTypeStrategy);
    assertEquals(courseTypeStrategy.typeStrategy(),ScholarshipType.COURSETYPE);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load language strategy")
  void   testLoadLanguageStrategy(){
    ScholarshipStrategy languageStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.LANGUAGE);
    assertNotNull(languageStrategy);
    assertEquals(languageStrategy.typeStrategy(),ScholarshipType.LANGUAGE);
  }

  @Test
  @DisplayName("Test ScholarshipContext, test to load status strategy")
  void testLoadStatusStrategy(){
    ScholarshipStrategy statusStrategy=scholarshipContext.getScholarshipStrategy(ScholarshipType.STATUS);
    assertNotNull(statusStrategy);
    assertEquals(statusStrategy.typeStrategy(),ScholarshipType.STATUS);
  }
}
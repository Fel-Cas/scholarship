package com.api.scholarships.repositories;

import com.api.scholarships.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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

  @BeforeEach
  void setUp(){
    //Save career
    Career careerSaved= careerRepository.save(Career
        .builder()
        .careerName("INGENIRIA DE SISTEMAS")
        .build()
    );

    //Save company
    Company companySaved = companyRepository.save(Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build()
    );

    // Save country
    Country countrySaved= countryRepository.save( Country
        .builder()
        .countryName("Nigeria")
        .abbreviation("NG")
        .build()
    );

    //Find Course type
    CourseType courseTypeFound=courseTypeRepository.findById(1L).get();

    //Save image
    Image imageSaved=imageRepository.save(Image.builder()
        .name("image1")
        .imageId("image1")
        .url("url")
        .build()
    );

    // Find Language
    Language languageFound=languageRepository.findById(1L).get();

    // Find status
    Status statusFound=statusRepository.findById(1L).get();

    scholarship=Scholarship.builder()
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate(new Date())
        .finishDate(new Date())
        .link("Esto es un link")
        .courseType(courseTypeFound)
        .country(countrySaved)
        .status(statusFound)
        .language(languageFound)
        .image(imageSaved)
        .company(companySaved)
        .careers(List.of(careerSaved))
        .build();
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to create a scholarship")
  void testCreate(){
    //given
    //when
    Scholarship scholarshipSaved=scholarshipRepository.save(scholarship);
    //then
    assertAll(
        ()->assertNotNull(scholarshipSaved),
        ()->assertThat(scholarshipSaved.getId()).isGreaterThan(0),
        ()->assertEquals(scholarship.getTitle(),scholarshipSaved.getTitle()),
        ()->assertEquals(scholarship.getDescription(),scholarshipSaved.getDescription()),
        ()->assertEquals(scholarship.getStartDate(),scholarshipSaved.getStartDate()),
        ()->assertEquals(scholarship.getFinishDate(),scholarshipSaved.getFinishDate()),
        ()->assertEquals(scholarship.getLink(),scholarshipSaved.getLink()),
        ()->assertNotNull(scholarship.getCourseType()),
        ()->assertNotNull(scholarship.getCountry()),
        ()->assertNotNull(scholarship.getStatus()),
        ()->assertNotNull(scholarship.getLanguage()),
        ()->assertNotNull(scholarship.getImage()),
        ()->assertNotNull(scholarship.getCompany()),
        ()->assertThat(scholarshipSaved.getCareers().size()).isGreaterThan(0)
    );
  }
}
package com.api.scholarships.services;

import com.api.scholarships.entities.*;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.implementation.ScholarshipServiceImp;
import com.api.scholarships.services.interfaces.*;
import com.api.scholarships.services.strategyScholarships.ScholarshipContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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
  private Scholarship scholarship;
  private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach
  void setUp() throws ParseException {
    //Course type
    courseType=CourseType.builder()
        .id(1L)
        .courseType("BOOTCAMP")
        .build();
    //Country
    country=Country.builder()
        .id(1L)
        .countryName("COLOMBIA")
        .abbreviation("COL")
        .build();
    //status
    status=Status.builder()
        .id(1L)
        .statusName("ACTIVO")
        .build();
    //language
    language=Language.builder()
        .id(1L)
        .languageName("ESPAÑOL")
        .build();
    //image
    image=Image.builder()
        .url("https://localhost:8080/images/profile")
        .imageId("dsdsdsd")
        .name("profile.png")
        .build();
    //career
    career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
    //company
    company=Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .image(image)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    scholarship=Scholarship.builder()
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate(format.parse("2023-01-01"))
        .finishDate(format.parse("2023-02-02"))
        .link("Esto es un link")
        .courseType(courseType)
        .country(country)
        .status(status)
        .language(language)
        .image(image)
        .company(company)
        .careers(List.of(career))
        .build();
  }
}
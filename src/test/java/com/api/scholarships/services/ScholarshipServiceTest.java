package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.entities.*;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.implementation.ScholarshipServiceImp;
import com.api.scholarships.services.interfaces.*;
import com.api.scholarships.services.strategyScholarships.ScholarshipContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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
        .id(1L)
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate(format.parse("2023-01-01"))
        .finishDate(format.parse("2023-02-02"))
        .link("http:localhost:6788/admin/api/scholarships")
        .courseType(courseType)
        .country(country)
        .status(status)
        .language(language)
        .image(image)
        .company(company)
        .careers(List.of(career))
        .build();
  }

  @Test
  @DisplayName("Test ScholarshipService, test to create a new scholarship")
  void testCreateScholarship() throws Exception{
    //given
    ScholarshipDTO scholarshipDTO=ScholarshipDTO.builder()
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate("2023-01-01")
        .finishDate("2023-02-02")
        .link("http:localhost:6788/admin/api/scholarships")
        .courseType("BOOTCAMP")
        .country("COLOMBIA")
        .language("ESPAÑOL")
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .company(1L)
        .careers(List.of("INGENIERIA DE SISTEMAS"))
        .build();
    given(careerService.findByName(anyString())).willReturn(career);
    given(courseTypeService.findByCourseType(anyString())).willReturn(courseType);
    given(countryService.findByName(anyString())).willReturn(country);
    given(statusService.findByName(anyString())).willReturn(status);
    given(languageService.findByName(anyString())).willReturn(language);
    given(imageService.save(any(MultipartFile.class))).willReturn(image);
    given(scholarshipRepository.save(any(Scholarship.class))).willReturn(scholarship);
    //when
    Scholarship scholarshipSaved=scholarshipService.create(scholarshipDTO);
    //then
    assertAll(
        ()->assertNotNull(scholarshipSaved),
        ()->assertEquals(scholarshipSaved.getDescription(), scholarship.getDescription()),
        ()->assertEquals(scholarshipSaved.getStartDate(), scholarship.getStartDate()),
        ()->assertEquals(scholarshipSaved.getFinishDate(), scholarship.getFinishDate()),
        ()->assertEquals(scholarshipSaved.getLink(), scholarship.getLink()),
        ()->assertNotNull(scholarshipSaved.getCourseType()),
        ()->assertNotNull(scholarshipSaved.getCountry()),
        ()->assertNotNull(scholarshipSaved.getStatus()),
        ()->assertNotNull(scholarshipSaved.getLanguage()),
        ()->assertNotNull(scholarshipSaved.getImage()),
        ()->assertNotNull(scholarshipSaved.getCompany()),
        ()->assertThat(scholarshipSaved.getCareers().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to check for an exception when trying to create a scholarship with wrong dates")
  void testFailCreate(){
    //given
    ScholarshipDTO scholarshipDTO=ScholarshipDTO.builder()
        .title("Mi titulo")
        .description("Descripción de la beca")
        .startDate("2023-01-01")
        .finishDate("2022-02-02")
        .link("http:localhost:6788/admin/api/scholarships")
        .courseType("BOOTCAMP")
        .country("COLOMBIA")
        .language("ESPAÑOL")
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .company(1L)
        .careers(List.of("INGENIERIA DE SISTEMAS"))
        .build();
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->scholarshipService.create(scholarshipDTO));
    //then
    assertNotNull(exception);
    assertEquals(Messages.MESSAGE_WRONG_DATES,exception.getMessage());
  }
}
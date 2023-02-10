package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.*;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.implementation.ScholarshipServiceImp;
import com.api.scholarships.services.interfaces.*;
import com.api.scholarships.services.strategyScholarships.ScholarshipContext;
import com.api.scholarships.services.strategyScholarships.ScholarshipStrategy;
import com.api.scholarships.services.strategyScholarships.ScholarshipType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
  @Mock
  private ScholarshipStrategy scholarshipStrategy;
  @Mock
  private CurrentUserService currentUserService;
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
    List<Career> careers=new ArrayList<>();

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
        .id(1L)
        .url("https://localhost:8080/images/profile")
        .imageId("dsdsdsd")
        .name("profile.png")
        .build();
    //career
    career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
    careers.add(career);
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
        .careers(careers)
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
    given(companyService.getOne(anyLong())).willReturn(company);
    willDoNothing().given(currentUserService).verifyCorrectUserInCompany(company);
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

  @Test
  @DisplayName("Test ScholarshipService, test to find a scholarship by id")
  void testFindById(){
    //given
    given(scholarshipRepository.findById(anyLong())).willReturn(Optional.of(scholarship));
    //when
    Scholarship scholarshipFound=scholarshipService.getById(anyLong());
    //then
    assertAll(
        ()->assertNotNull(scholarshipFound),
        ()->assertEquals(scholarshipFound.getDescription(), scholarship.getDescription()),
        ()->assertEquals(scholarshipFound.getStartDate(), scholarship.getStartDate()),
        ()->assertEquals(scholarshipFound.getFinishDate(), scholarship.getFinishDate()),
        ()->assertEquals(scholarshipFound.getLink(), scholarship.getLink()),
        ()->assertNotNull(scholarshipFound.getCourseType()),
        ()->assertNotNull(scholarshipFound.getCountry()),
        ()->assertNotNull(scholarshipFound.getStatus()),
        ()->assertNotNull(scholarshipFound.getLanguage()),
        ()->assertNotNull(scholarshipFound.getImage()),
        ()->assertNotNull(scholarshipFound.getCompany()),
        ()-> AssertionsForClassTypes.assertThat(scholarshipFound.getCareers().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to check if  an exception occurs when searching a scholarship by id and it doesn't exist")
  void testGetByIdWhenScholarshipNotFound() {
    //given
    given(scholarshipRepository.findById(1L)).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->scholarshipService.getById(1L));
    //then
    assertNotNull(exception);
    assertEquals(Messages.MESSAGE_SCHOLARSHIP_NOT_FOUND.formatted(1L), exception.getMessage());
    verify(scholarshipRepository,times(1)).findById(1L);
  }

  @Test
  @DisplayName("Test ScholarshipService, test to update a scholarship")
  void testUpdateScholarship() throws ParseException {
    //given
    ScholarshipUpdateDTO scholarshipUpdateDTO=ScholarshipUpdateDTO.builder()
        .title("BIG DATA SCHOLARSHIP")
        .description("Esta es una beca especializarse en la manipulación de datos")
        .link("http://loclahost:9090/big-data")
        .startDate(format.parse("2022-11-12"))
        .finishDate(format.parse("2023-02-14 "))
        .build();
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    scholarship.setTitle(scholarshipUpdateDTO.getTitle());
    scholarship.setDescription(scholarshipUpdateDTO.getDescription());
    scholarship.setLink(scholarshipUpdateDTO.getLink());
    scholarship.setStartDate(scholarshipUpdateDTO.getStartDate());
    scholarship.setFinishDate(scholarshipUpdateDTO.getFinishDate());
    given(scholarshipRepository.save(any(Scholarship.class))).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.update(scholarshipUpdateDTO,1L);
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdateDTO.getTitle(),scholarshipUpdated.getTitle()),
        ()->assertEquals(scholarshipUpdateDTO.getDescription(),scholarshipUpdated.getDescription()),
        ()->assertEquals(scholarshipUpdateDTO.getLink(),scholarshipUpdated.getLink()),
        ()->assertEquals(scholarshipUpdateDTO.getStartDate(),scholarshipUpdated.getStartDate()),
        ()->assertEquals(scholarshipUpdateDTO.getFinishDate(),scholarshipUpdated.getFinishDate()),
        ()->assertNotNull(scholarshipUpdated.getCourseType()),
        ()->assertNotNull(scholarshipUpdated.getCourseType()),
        ()->assertNotNull(scholarshipUpdated.getCountry()),
        ()->assertNotNull(scholarshipUpdated.getStatus()),
        ()->assertNotNull(scholarshipUpdated.getLanguage()),
        ()->assertNotNull(scholarshipUpdated.getImage()),
        ()->assertNotNull(scholarshipUpdated.getCompany()),
        ()-> AssertionsForClassTypes.assertThat(scholarshipUpdated.getCareers().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to delete a scholarship")
  void deleteScholarship() throws IOException {
    //given
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    willDoNothing().given(imageService).delete(1L);
    willDoNothing().given(scholarshipRepository).delete(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    scholarshipService.delete(1L);
    //then
    verify(scholarshipRepository,times(1)).delete(scholarship);
    verify(imageService, times(1)).delete(1L);
  }

  @Test
  @DisplayName("Test ScholarshipService, test to find all scholarships")
  void testFindAll() throws ParseException {
    //given
    ScholarshipDTOResponse scholarshipDTOResponse=ScholarshipDTOResponse.builder()
        .title("Mi titulo")
        .id(1L)
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

    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    given(scholarshipContext.getScholarshipStrategy(ScholarshipType.DEFAULT)).willReturn(scholarshipStrategy);
    Page<Scholarship> scholarships=new PageImpl<>(List.of(scholarship));
    given(scholarshipStrategy.findScholarshipsByCondition(pageable,1L)).willReturn(scholarships);

    given(scholarshipMapper.scholarshipListToScholarshipDTOResponseList(List.of(scholarship))).willReturn(List.of(scholarshipDTOResponse));
    //when
    ScholarshipResponse response=scholarshipService.findAll(0,10,"id","ASC",ScholarshipType.DEFAULT,1L);
    //then
    assertAll(
        ()-> assertNotNull(response),
        ()->assertThat(response.getContent()).hasSize(1),
        ()->assertEquals(0,response.getNumberPage()),
        ()->assertEquals(1,response.getSizePage()),
        ()->assertEquals(1,response.getTotalPages()),
        ()->assertEquals(1,response.getTotalElements()),
        ()->assertTrue(response.isLastOne())
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the course type of a scholarship")
  void changeCourseTypeOfScholarshipTest() {
    //given
    CourseType courseTypeFound=new CourseType(2L,"MAESTRIA");
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(courseTypeService.findById(2L)).willReturn(courseTypeFound);
    scholarship.setCourseType(courseTypeFound);
    given(scholarshipRepository.save(scholarship)).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.changeCourseType(1L,2L);
    //then
    assertAll(
        ()-> assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getCourseType().getId(),courseTypeFound.getId()),
        ()->assertEquals(scholarshipUpdated.getCourseType().getCourseType(),courseTypeFound.getCourseType())
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the country of  a scholarship")
  void  changeCountryOfScholarshipTest() {
    //given
    Country countryFound=new Country(2L,"MEXICO","MEX");
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(countryService.findById(2L)).willReturn(countryFound);
    scholarship.setCountry(countryFound);
    given(scholarshipRepository.save(scholarship)).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.changeCountry(1L,2L);
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getCountry().getId(),countryFound.getId()),
        ()->assertEquals(scholarshipUpdated.getCountry().getCountryName(),countryFound.getCountryName()),
        ()->assertEquals(scholarshipUpdated.getCountry().getAbbreviation(),countryFound.getAbbreviation())
    );
  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the status of a scholarship")
  void changeStatusOfScholarshipTest() {
    //given
    Status statusFound=new Status(2L,"CANCELADO");
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(statusService.findById(2L)).willReturn(statusFound);
    scholarship.setStatus(statusFound);
    given(scholarshipRepository.save(scholarship)).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.changeStatus(1L,2L);
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getStatus().getId(),statusFound.getId()),
        ()->assertEquals(scholarshipUpdated.getStatus().getStatusName(),statusFound.getStatusName())
    );

  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the language of a scholarship ")
  void changeLanguageOfScholarshipTest() {
    //given
    Language languageFound=new Language(2L,"INGLES");
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(languageService.findById(2L)).willReturn(languageFound);
    scholarship.setLanguage(languageFound);
    given(scholarshipRepository.save(scholarship)).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.changeLanguage(1L,2L);
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getLanguage().getId(),languageFound.getId()),
        ()->assertEquals(scholarshipUpdated.getLanguage().getLanguageName(),languageFound.getLanguageName())
    );

  }

  @Test
  @DisplayName("Test ScholarshipService, test to change the image of a scholarship")
  void changeImageOfScholarshipTest() throws IOException {
    //given
    Image imageCreated=Image
        .builder()
        .id(2L)
        .url("/home/user/test.png")
        .name("image.jpeg")
        .imageId("wewe433erere")
        .build();
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(imageService.save(any(MultipartFile.class))).willReturn(imageCreated);
    scholarship.setImage(imageCreated);
    given(scholarshipRepository.save(scholarship)).willReturn(scholarship);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.changeImage(1L,new MockMultipartFile( "image", "image.jpeg", "image/jpeg","".getBytes()));
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getImage().getId(),imageCreated.getId()),
        ()->assertEquals(scholarshipUpdated.getImage().getImageId(),imageCreated.getImageId()),
        ()->assertEquals(scholarshipUpdated.getImage().getName(),imageCreated.getName()),
        ()->assertEquals(scholarshipUpdated.getImage().getUrl(),imageCreated.getUrl()));
  }

  @Test
  @DisplayName("Test ScholarshipService, Test to add a career of a scholarship")
  void addCareerTest() throws ParseException {
    //given

    Career careerFound=new Career(2L,"EDUCACIÓN");
    Scholarship scholarshipResponse=Scholarship.builder()
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
        .careers(List.of(career, careerFound))
        .build();


    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(careerService.findById(2L)).willReturn(careerFound);
    given(scholarshipRepository.save(any(Scholarship.class))).willReturn(scholarshipResponse);
    willDoNothing().given(currentUserService).verifyCorrectUserInScholarship(any(Scholarship.class));
    //when
    Scholarship scholarshipUpdated=scholarshipService.addCareer(1L,2L);
    //then
    assertAll(
        ()->assertNotNull(scholarshipUpdated),
        ()->assertEquals(scholarshipUpdated.getCareers().size(),2));
  }

  @Test
  @DisplayName("Test ScholarshipService, test to check for an exception to when trying to add a career to a scholarship but it is already assocciated")
  void addScholarshipverifyException(){
    //given
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(careerService.findById(2L)).willReturn(career);
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->scholarshipService.addCareer(1L,2L));
    //then
    assertEquals(Messages.MESSAGE_DUPLICATE_CAREER.formatted(career.getCareerName()),exception.getMessage());
  }

  @Test
  @DisplayName("Test ScholarshipService, test to remove a career of a scholarship")
  void removeCareerOfScholarship() throws ParseException {
    //given
    Career careerFound=new Career(2L,"EDUCACIÓN");
    scholarship.getCareers().add(careerFound);
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(careerService.findById(2L)).willReturn(careerFound);
    Scholarship scholarshipResponse=Scholarship.builder()
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
    given(scholarshipRepository.save(scholarshipResponse)).willReturn(scholarshipResponse);
    //when
    Scholarship scholarshipUpdated=scholarshipService.removeCareer(1L,2L);
    //then
    assertNotNull(scholarshipUpdated);
    assertThat(scholarshipUpdated.getCareers().size()).isEqualTo(1);
  }

  @Test
  @DisplayName("Test ScholarshipService, test to check an exception when tryin to remove a career of a scholarship, but the scholarship has only a career")
  void removeCareerExceptionTest(){
    //given
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->scholarshipService.removeCareer(1L,2L));
    //then
    assertEquals(Messages.MESSAGE_CANNOT_REMOVE_CAREER,exception.getMessage());
  }

  @Test
  @DisplayName("Test ScholarshipService, test to check an exception when tryin to remove a career of a scholarship, but the career isn't associated with the scholarship")
  void  removeCareerExceptionTest2(){
    //given
    Career career=new Career(2L,"EDUCACIÓN");
    scholarship.getCareers().add(career);
    Career careerFound=new Career(3L,"LITERATURA");
    given(scholarshipRepository.findById(1L)).willReturn(Optional.of(scholarship));
    given(careerService.findById(3L)).willReturn(careerFound);
    //when
    BadRequestException exception=assertThrows(BadRequestException.class,()->scholarshipService.removeCareer(1L,3L));
    //then
    assertEquals(Messages.MESSAGE_REMOVE_NO_ASSOCIATE_CAREER.formatted(careerFound.getCareerName(),1L),exception.getMessage());

  }
}
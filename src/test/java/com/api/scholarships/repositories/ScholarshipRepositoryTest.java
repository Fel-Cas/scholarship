package com.api.scholarships.repositories;

import com.api.scholarships.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
  @DisplayName("Test ScholarshipRepository, test to find a scholarship by id")
  void testFindById(){
    //given
    Scholarship scholarshipSaved=scholarshipRepository.save(scholarship);
    //when
    Optional<Scholarship> scholarshipFound=scholarshipRepository.findById(scholarshipSaved.getId());
    //then
    assertAll(
        ()->assertNotNull(scholarshipFound),
        ()->assertTrue(scholarshipFound.isPresent()),
        ()->assertEquals(scholarshipSaved.getId(),scholarshipFound.get().getId()),
        ()->assertEquals(scholarship.getTitle(),scholarshipFound.get().getTitle()),
        ()->assertEquals(scholarship.getDescription(),scholarshipFound.get().getDescription()),
        ()->assertEquals(scholarship.getStartDate(),scholarshipFound.get().getStartDate()),
        ()->assertEquals(scholarship.getFinishDate(),scholarshipFound.get().getFinishDate()),
        ()->assertEquals(scholarship.getLink(),scholarshipFound.get().getLink()),
        ()->assertNotNull(scholarshipFound.get().getCourseType()),
        ()->assertNotNull(scholarshipFound.get().getCountry()),
        ()->assertNotNull(scholarshipFound.get().getStatus()),
        ()->assertNotNull(scholarshipFound.get().getLanguage()),
        ()->assertNotNull(scholarshipFound.get().getImage()),
        ()->assertNotNull(scholarshipFound.get().getCompany()),
        ()->assertThat(scholarshipSaved.getCareers().size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find all scholarships")
  void testFindAll(){
    //given
    scholarshipRepository.save(scholarship);
    //when
    List<Scholarship> scholarshipsFound = scholarshipRepository.findAll();
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.size()).isGreaterThan(0)
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find scholarships by course type")
  void testFindScholarshipsByCourseType(){
    //given
    scholarshipRepository.save(scholarship);
    CourseType courseTypeFound=scholarship.getCourseType();
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByCourseType(courseTypeFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find scholarships by Country")
  void testFindScholarshipsByCountry(){
    //given
    scholarshipRepository.save(scholarship);
    Country countryFound=scholarship.getCountry();
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByCountry(countryFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find scholarships by status")
  void testFindScholarshipsByStatus(){
    //given
    scholarshipRepository.save(scholarship);
    Status statusFound=scholarship.getStatus();
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByStatus(statusFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find scholarships by language")
  void  testFindScholarshipsByLanguage(){
    //given
    scholarshipRepository.save(scholarship);
    Language languageFound=scholarship.getLanguage();
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByLanguage(languageFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }

  @Test
  @DisplayName("Test ScholarshipRepository, test to find scholarship by company")
  void  testFindScholarshipsByCompany(){
    //given
    scholarshipRepository.save(scholarship);
    Company companyFound=scholarship.getCompany();
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByCompany(companyFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }

  @Test
  @DisplayName("Test ScolarshipRepository, test to find scholarships by career")
  void  testFindScholarshipsByCareer(){
    //given
    scholarshipRepository.save(scholarship);
    Career careerFound=scholarship.getCareers().get(0);
    Sort sortDirection=Sort.by("id").ascending();
    Pageable pageable= PageRequest.of(0,10,sortDirection);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipRepository.findByCareers(careerFound,pageable);
    //then
    assertAll(
        ()->assertNotNull(scholarshipsFound),
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertTrue(scholarshipsFound.isLast()),
        ()->assertThat(scholarshipsFound.getTotalPages()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getNumber()).isEqualTo(0),
        ()->assertEquals(10,scholarshipsFound.getSize())
    );
  }
}
package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.entities.*;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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
  private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach
  void setUp() throws ParseException {
    List<Career> careers=new ArrayList<>();

    //Course type
    CourseType courseType= CourseType.builder()
        .id(1L)
        .courseType("BOOTCAMP")
        .build();
    //Country
    Country country= Country.builder()
        .id(1L)
        .countryName("COLOMBIA")
        .abbreviation("COL")
        .build();
    //status
    Status status= Status.builder()
        .id(1L)
        .statusName("ACTIVO")
        .build();
    //language
    language=Language.builder()
        .id(1L)
        .languageName("ESPAÑOL")
        .build();
    //image
    Image image=Image.builder()
        .id(1L)
        .url("https://localhost:8080/images/profile")
        .imageId("dsdsdsd")
        .name("profile.png")
        .build();
    //career
    Career career=Career.builder()
        .id(1L)
        .careerName("INGENIERIA DE SISTEMAS")
        .build();
    careers.add(career);
    //company
    Company company=Company.builder()
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
  @DisplayName("Test ScholarshipLanguage, test to find scholarships by language")
  void testToFindScholarshipsByLanguage(){
    //given
    given(languageService.findById(1L)).willReturn(language);
    Page<Scholarship> scholarships=new PageImpl<>(List.of(scholarship));
    Pageable pageable= PageRequest.of(0,1, Sort.by(Sort.Direction.DESC,"id"));
    given(scholarshipRepository.findByLanguage(language,pageable)).willReturn(scholarships);
    //when
    Page<Scholarship> scholarshipsFound=scholarshipLanguage.findScholarshipsByCondition(pageable,1L);
    //then
    assertAll(
        ()->assertThat(scholarshipsFound.getContent().size()).isGreaterThan(0),
        ()->assertThat(scholarshipsFound.getTotalElements()).isEqualTo(1),
        ()->assertEquals(0,scholarshipsFound.getNumber()),
        ()->assertEquals(1,scholarshipsFound.getTotalPages()),
        ()->assertEquals(1,scholarshipsFound.getSize()),
        ()->assertTrue(scholarshipsFound.isLast())

    );
  }
}
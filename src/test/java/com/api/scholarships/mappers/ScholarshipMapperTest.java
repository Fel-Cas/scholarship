package com.api.scholarships.mappers;

import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScholarshipMapperTest {

  private ScholarshipMapper scholarshipMapper=Mappers.getMapper( ScholarshipMapper.class);
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
    Language language=Language.builder()
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
  @DisplayName("Test ScholarshipMapper, test to pass from Scholarship class to ScholarshipDTOResponse class")
  void testToPassFromScholarshipToScholarshipDTOResponse(){
    //given
    //when
    ScholarshipDTOResponse scholarshipDTOResponse=scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarship);
    //then
    assertAll(
        ()->assertNotNull(scholarshipDTOResponse),
        ()->assertEquals(scholarship.getId(),scholarshipDTOResponse.getId()),
        ()->assertEquals(scholarship.getTitle(),scholarshipDTOResponse.getTitle()),
        ()->assertEquals(scholarship.getDescription(),scholarshipDTOResponse.getDescription()),
        ()->assertEquals(scholarship.getStartDate(),scholarshipDTOResponse.getStartDate()),
        ()->assertEquals(scholarship.getFinishDate(),scholarshipDTOResponse.getFinishDate()),
        ()->assertEquals(scholarship.getLink(),scholarshipDTOResponse.getLink()),
        ()->assertEquals(scholarship.getCourseType(),scholarshipDTOResponse.getCourseType()),
        ()->assertEquals(scholarship.getStatus(),scholarshipDTOResponse.getStatus()),
        ()->assertEquals(scholarship.getImage(),scholarshipDTOResponse.getImage()),
        ()->assertEquals(scholarship.getCountry(),scholarshipDTOResponse.getCountry()),
        ()->assertEquals(scholarship.getLanguage(),scholarshipDTOResponse.getLanguage()),
        ()->assertEquals(scholarship.getCompany(),scholarshipDTOResponse.getCompany()),
        ()->assertThat(scholarshipDTOResponse.getCareers().size()).isEqualTo(1)

    );
  }
}
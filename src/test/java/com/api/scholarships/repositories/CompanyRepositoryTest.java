package com.api.scholarships.repositories;

import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@DataJpaTest
@ActiveProfiles("test")
class CompanyRepositoryTest {

  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ImageRepository imageRepository;
  private Company company;
  private User user;
  private Image image;

  @BeforeEach
  void init() {
    user = User.builder()
        .name("Juanito")
        .surname("Perez")
        .password("123456")
        .dni("5865486758697")
        .email("email@emial.com")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    image = Image.builder()
        .id(1L)
        .name("image")
        .imageId("imageId")
        .url("url")
        .build();

    User userSaved = this.userRepository.save(user);
    Image imageSaved = this.imageRepository.save(image);


    company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(List.of(userSaved))
        .image(imageSaved)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

  }

  @Test
  @DisplayName("Test CompanyRepository,Test save a new company")
  void testSaveCompany() {
    //given
    //when
    Company companySaved = companyRepository.save(company);
    //then
    assertNotNull(companySaved);
    assertThat(companySaved.getId()).isGreaterThan(0);
    assertNotNull(companySaved.getCreatedAt());
    assertNotNull(companySaved.getUpdatedAt());
    assertNotNull(companySaved.getUsers());
    assertThat(companySaved.getUsers().size()).isGreaterThan(0);
    assertNotNull(companySaved.getImage());
  }

  @Test
  @DisplayName("Test CompanyRepository,Test find a company by id")
  void testFindCompanyById() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    Optional<Company> companyFound = companyRepository.findById(companySaved.getId());
    //then
    assertAll(
        () -> assertTrue(companyFound.isPresent()),
        () -> assertEquals(companySaved.getId(), companyFound.get().getId()),
        () -> assertEquals(companySaved.getName(), companyFound.get().getName()),
        () -> assertEquals(companySaved.getAddress(), companyFound.get().getAddress()),
        () -> assertEquals(companySaved.getPhone(), companyFound.get().getPhone()),
        () -> assertEquals(companySaved.getEmail(), companyFound.get().getEmail()),
        () -> assertEquals(companySaved.getCreatedAt(), companyFound.get().getCreatedAt()),
        () -> assertEquals(companySaved.getUpdatedAt(), companyFound.get().getUpdatedAt()),
        () -> assertNotNull(companyFound.get().getUsers()),
        () -> assertThat(companyFound.get().getUsers().size()).isGreaterThan(0),
        () -> assertNotNull(companyFound.get().getImage())
    );
  }

  @Test
  @DisplayName("Test CompanyRepository,Test to check if a company exists by name")
  void testExistsByName() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean exists = companyRepository.existsByName(companySaved.getName());
    boolean notExists = companyRepository.existsByName(anyString());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository,Test to check if a company exists by email")
  void testExistsByEmail() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean exists = companyRepository.existsByEmail(companySaved.getEmail());
    boolean notExists = companyRepository.existsByEmail(anyString());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository,Test to check if a company exists by phone")
  void testExistsByPhone() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean exists = companyRepository.existsByPhone(companySaved.getPhone());
    boolean notExists = companyRepository.existsByPhone(anyString());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository,Test to check if a company exists by user")
  void testExistsByUser() {
    //given
    Company companySaved = companyRepository.save(company);
    User userSaved=this.userRepository.save(
        User.builder()
            .name("Guillermo")
            .surname("Perez")
            .password("123456")
            .dni("34567888885")
            .email("p@emial.com")
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build()
    );
    //when
    boolean exists = companyRepository.existsByUsers(companySaved.getUsers().get(0));
    boolean notExists = companyRepository.existsByUsers(userSaved);
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository, Test to check if there is a company saved with an emial but not with a specific id")
  void testExistsByEmailAndIdNot() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean notExists = companyRepository.existsByEmailAndIdNot(companySaved.getEmail(), companySaved.getId());
    boolean exists = companyRepository.existsByEmailAndIdNot(companySaved.getEmail(), anyLong());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository, Test to check if there is a company saved with a phone but not with a specific id")
  void testExistsByPhoneAndIdNot() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean notExists = companyRepository.existsByPhoneAndIdNot(companySaved.getPhone(), companySaved.getId());
    boolean exists = companyRepository.existsByPhoneAndIdNot(companySaved.getPhone(), anyLong());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository, Test to check if there is a company saved with a name but not with a specific id")
  void testExistsByNameAndIdNot() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    boolean notExists = companyRepository.existsByNameAndIdNot(companySaved.getName(), companySaved.getId());
    boolean exists = companyRepository.existsByNameAndIdNot(companySaved.getName(), anyLong());
    //then
    assertTrue(exists);
    assertFalse(notExists);
  }

  @Test
  @DisplayName("Test CompanyRepository, Test to find all companies")
  void testFindAll() {
    //given
    Company companySaved = companyRepository.save(company);
    //when
    List<Company> companies = companyRepository.findAll();
    //then
    assertAll(
        () -> assertNotNull(companies),
        () -> assertThat(companies.size()).isGreaterThan(0),
        () -> assertEquals(companySaved.getId(), companies.get(0).getId()),
        () -> assertEquals(companySaved.getName(), companies.get(0).getName()),
        () -> assertEquals(companySaved.getAddress(), companies.get(0).getAddress()),
        () -> assertEquals(companySaved.getPhone(), companies.get(0).getPhone()),
        () -> assertEquals(companySaved.getEmail(), companies.get(0).getEmail()),
        () -> assertEquals(companySaved.getCreatedAt(), companies.get(0).getCreatedAt()),
        () -> assertEquals(companySaved.getUpdatedAt(), companies.get(0).getUpdatedAt()),
        () -> assertNotNull(companies.get(0).getUsers()),
        () -> assertThat(companies.get(0).getUsers().size()).isGreaterThan(0),
        () -> assertNotNull(companies.get(0).getImage())
    );
  }
}
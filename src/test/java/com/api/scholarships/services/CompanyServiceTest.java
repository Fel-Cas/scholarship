package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.repositories.CompanyRepository;
import com.api.scholarships.services.implementation.CompanyServiceImp;
import com.api.scholarships.services.interfaces.ImageService;
import com.api.scholarships.services.interfaces.UserService;
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

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CompanyServiceTest {
  @Mock
  private CompanyRepository companyRepository;
  @Mock
  private CompanyMapper companyMapper;
  @Mock
  private ImageService imageService;
  @Mock
  private UserService userService;
  @InjectMocks
  private CompanyServiceImp companyService;
  private Company company;

  @BeforeEach()
  void setUp() {
    company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(List.of(
            User.builder()
                .name("Juanito")
                .surname("Perez")
                .password("123456")
                .dni("5865486758697")
                .email("email@emial.com")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build()
        ))
        .image(
            Image.builder()
                .id(1L)
                .name("image")
                .imageId("imageId")
                .url("url")
                .build()
        )
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  @Test
  @DisplayName("Test CompanyService, test to save a new company")
  void save() throws IOException {
    //given
    CompanyDTO companyDTO = CompanyDTO.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .users(List.of(1L))
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .build();

    given(companyRepository.existsByEmail(anyString())).willReturn(false);
    given(companyRepository.existsByName(anyString())).willReturn(false);
    given(companyRepository.existsByPhone(anyString())).willReturn(false);

    given(userService.getById(anyLong())).willReturn(
        User.builder()
        .name("Juanito")
        .surname("Perez")
        .password("123456")
        .dni("5865486758697")
        .email("email@emial.com")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build()
    );

    given(companyRepository.existsByUsers(any(User.class))).willReturn(false);
    given(imageService.save(any(MultipartFile.class))).willReturn(
        Image.builder()
            .id(1L)
            .name("image")
            .imageId("imageId")
            .url("url")
            .build()
    );

    given(companyRepository.save(any(Company.class))).willReturn(company);
    //when
    Company companySaved = companyService.create(companyDTO);
    //then
    assertNotNull(companySaved);
    assertNotNull(companySaved.getImage());
    assertNotNull(companySaved.getUsers());
    assertThat(companySaved.getUsers().size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test CompanyService, test gets an error when trying to save a new company with an email already registered")
  void saveWithExistingEmail() throws IOException {
    //given
    CompanyDTO companyDTO = CompanyDTO.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .users(List.of(1L))
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .build();

    given(companyRepository.existsByEmail(anyString())).willReturn(true);
    given(companyRepository.existsByName(anyString())).willReturn(false);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.create(companyDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL.formatted(companyDTO.getEmail()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test gets an error when trying to save a new company with an name already registered")
  void saveWithExistingName() throws IOException {
    //given
    CompanyDTO companyDTO = CompanyDTO.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .users(List.of(1L))
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .build();

    given(companyRepository.existsByName(anyString())).willReturn(true);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.create(companyDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_NAME.formatted(companyDTO.getName()), exception.getMessage());
  }
}
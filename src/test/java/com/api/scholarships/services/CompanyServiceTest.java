package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.repositories.CompanyRepository;
import com.api.scholarships.services.implementation.CompanyServiceImp;
import com.api.scholarships.services.interfaces.CurrentUserService;
import com.api.scholarships.services.interfaces.ImageService;
import com.api.scholarships.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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
  @Mock
  private CurrentUserService currentUserService;
  @InjectMocks
  private CompanyServiceImp companyService;
  private Company company;

  @BeforeEach()
  void setUp() {

    List<User> users=new ArrayList<>();
    users.add(
        User.builder()
            .id(1L)
            .name("Juanito")
            .surname("Perez")
            .password("123456")
            .dni("5865486758697")
            .email("email@emial.com")
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build()
    );

    company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(users)
        .image(
            Image.builder()
                .id(1L)
                .name("image")
                .imageId("imageId")
                .url("url")
                .build()
        )
        .scholarships(List.of(
            Scholarship.builder()
                .image(Image.builder()
                    .id(1L)
                    .name("image")
                    .imageId("imageId")
                    .url("url")
                    .build())
                .build()
        ))
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

  @Test
  @DisplayName("Test CompanyService, test gets an error when trying to save a new company with an phone already registered")
  void saveWithExistingPhone() throws IOException {
    //given
    CompanyDTO companyDTO = CompanyDTO.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .users(List.of(1L))
        .image(new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()))
        .build();

    given(companyRepository.existsByName(anyString())).willReturn(false);
    given(companyRepository.existsByEmail(anyString())).willReturn(false);
    given(companyRepository.existsByPhone(anyString())).willReturn(true);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.create(companyDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_PHONE.formatted(companyDTO.getPhone()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService,  test to get an error when trying to save a new company with an user already is registered with other company")
  void saveWithExistingUser() throws IOException {
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

    given(companyRepository.existsByUsers(any(User.class))).willReturn(true);

    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.create(companyDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_ADD_USER.formatted(companyDTO.getUsers().get(0)), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to find a company by id")
  void findOne(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    //when
    Company companyFound = companyService.getOne(anyLong());
    //then
    assertAll(
        () -> assertNotNull(companyFound),
        () -> assertNotNull(companyFound.getImage()),
        () -> assertNotNull(companyFound.getUsers()),
        () -> assertThat(companyFound.getUsers().size()).isGreaterThan(0),
        ()-> assertEquals(company.getId(), companyFound.getId()),
        ()-> assertEquals(company.getName(), companyFound.getName()),
        ()-> assertEquals(company.getAddress(), companyFound.getAddress()),
        ()-> assertEquals(company.getPhone(), companyFound.getPhone()),
        ()-> assertEquals(company.getEmail(), companyFound.getEmail()),
        ()-> assertEquals(company.getCreatedAt(), companyFound.getCreatedAt()),
        ()-> assertEquals(company.getUpdatedAt(), companyFound.getUpdatedAt())
    );
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to find a company by id and not found")
  void findOneNotFound(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.empty());
    //when
    NotFoundException exception = assertThrows(NotFoundException.class, () -> companyService.getOne(1L));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_NOT_FOUND.formatted(1L), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to find all companies")
  void findAll(){
    //given
    CompanyDTOResponse companyDTOResponse=CompanyDTOResponse.builder()
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

    Page<Company> companies = new PageImpl<>(List.of(company));
    given(companyRepository.findAll(any(Pageable.class))).willReturn(companies);
    given(companyMapper.companyToCompanyDTOResponse(List.of(company))).willReturn(List.of(companyDTOResponse));
    //when
    CompanyResponse companiesFound = companyService.getAll(0,10, "id", "ASC");
    //then
    assertAll(
        ()->assertNotNull(companiesFound),
        ()->assertThat(companiesFound.getContent().size()).isGreaterThan(0),
        ()->assertEquals(0,companiesFound.getNumberPage()),
        ()->assertEquals(1,companiesFound.getSizePage()),
        ()->assertEquals(1,companiesFound.getTotalPages()),
        ()->assertEquals(1,companiesFound.getTotalElements()),
        ()->assertTrue(companiesFound.isLastOne())
    );
  }

  @Test
  @DisplayName("Test CompanyService, test to update a company")
  void update(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByEmailAndIdNot(anyString(),anyLong())).willReturn(false);
    given(companyRepository.existsByNameAndIdNot(anyString(),anyLong())).willReturn(false);
    given(companyRepository.existsByPhoneAndIdNot(anyString(),anyLong())).willReturn(false);
    willDoNothing().given(currentUserService).verifyCorrectUserInCompany(any(Company.class));

    CompanyUpdateDTO companyUpdateDTO = CompanyUpdateDTO.builder()
        .name("Company S.A.S.")
        .address("Bogota,Antioquia")
        .phone("13444545555")
        .email("email@emailcom")
        .build();

    company.setName(companyUpdateDTO.getName());
    company.setAddress(companyUpdateDTO.getAddress());
    company.setPhone(companyUpdateDTO.getPhone());
    company.setEmail(companyUpdateDTO.getEmail());

    given(companyRepository.save(any(Company.class))).willReturn(company);
    //when
    Company companyUpdated = companyService.update(1L, companyUpdateDTO);
    //then
    assertAll(
        () -> assertNotNull(companyUpdated),
        () -> assertNotNull(companyUpdated.getImage()),
        () -> assertNotNull(companyUpdated.getUsers()),
        () -> assertThat(companyUpdated.getUsers().size()).isGreaterThan(0),
        ()-> assertEquals(companyUpdateDTO.getName().toUpperCase(), companyUpdated.getName()),
        ()-> assertEquals(companyUpdateDTO.getAddress().toUpperCase(), companyUpdated.getAddress()),
        ()-> assertEquals(companyUpdateDTO.getPhone(), companyUpdated.getPhone()),
        ()-> assertEquals(companyUpdateDTO.getEmail(), companyUpdated.getEmail())
    );
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to update a company with a name that already exists")
  void updateWithExistingName(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByNameAndIdNot(anyString(),anyLong())).willReturn(true);

    CompanyUpdateDTO companyUpdateDTO = CompanyUpdateDTO.builder()
        .name("Company S.A.S.")
        .address("Bogota,Antioquia")
        .phone("13444545555")
        .email("email@emailcom")
        .build();

    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.update(1L, companyUpdateDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_NAME.formatted(companyUpdateDTO.getName()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to update a company with an email that already exists")
  void updateWithExistingEmail(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByEmailAndIdNot(anyString(),anyLong())).willReturn(true);
    given(companyRepository.existsByNameAndIdNot(anyString(),anyLong())).willReturn(false);

    CompanyUpdateDTO companyUpdateDTO = CompanyUpdateDTO.builder()
        .name("Company S.A.S.")
        .address("Bogota,Antioquia")
        .phone("13444545555")
        .email("email@emailcom")
        .build();
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.update(1L, companyUpdateDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL.formatted(companyUpdateDTO.getEmail()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to update a company with a phone that already exists")
  void updateWithExistingPhone(){
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByEmailAndIdNot(anyString(),anyLong())).willReturn(false);
    given(companyRepository.existsByNameAndIdNot(anyString(),anyLong())).willReturn(false);
    given(companyRepository.existsByPhoneAndIdNot(anyString(),anyLong())).willReturn(true);

    CompanyUpdateDTO companyUpdateDTO = CompanyUpdateDTO.builder()
        .name("Company S.A.S.")
        .address("Bogota,Antioquia")
        .phone("13444545555")
        .email("email@emailcom")
        .build();
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.update(1L, companyUpdateDTO));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_PHONE.formatted(companyUpdateDTO.getPhone()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to delete a company")
  void testDelete() throws IOException {
    //given
    company.setUsers(Collections.emptyList());
    company.setScholarships(Collections.emptyList());
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    willDoNothing().given(companyRepository).delete(any(Company.class));
    //when
    companyService.delete(1L);
    //then
    verify(companyRepository, times(1)).delete(company);
  }

  @Test
  @DisplayName("Test CompanyService, test to verify error when trying to delete a company with scholarships")
  void testDeleteWithScholarships() {
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.delete(1L));
    //then
    assertEquals(Messages.MESSAGE_CANNOT_DELETE_COMPANY_WITH_SCHOLARSHIPS.formatted(company.getName()), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to verify error when trying to delete a company with users")
  void testDeleteWithUsers() {
    //given
    company.setScholarships(Collections.emptyList());
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    //whe
    BadRequestException exception=assertThrows(BadRequestException.class, () -> companyService.delete(1L));
    //then
    assertEquals(Messages.MESSAGE_CANNOT_DELETE_COMPANY_WITH_USERS,exception.getMessage());

  }


  @Test
  @DisplayName("Test CompanyService, test to add an user to a company")
  void addUser(){
    //given
    User user= User.builder()
        .id(2L)
        .name("Ricardo")
        .surname("Richie")
        .password("123456")
        .dni("5865486758697")
        .email("email@emial.com")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByUsers(any(User.class))).willReturn(false);
    given(userService.getById(anyLong())).willReturn(user);
    given(companyRepository.save(any(Company.class))).willReturn(company);
    //when
    Company companyUpdated = companyService.addUser(1L, 2L);
    //then
    assertAll(
        () -> assertNotNull(companyUpdated),
        () -> assertNotNull(companyUpdated.getUsers()),
        () -> assertThat(companyUpdated.getUsers().size()).isEqualTo(2)
    );
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to add an user who already belongs to a company")
  void failAddUser(){
    //given
    User user= User.builder()
        .id(2L)
        .name("Ricardo")
        .surname("Richie")
        .password("123456")
        .dni("5865486758697")
        .email("email@emial.com")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.existsByUsers(any(User.class))).willReturn(true);
    given(userService.getById(anyLong())).willReturn(user);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.addUser(1L, 2L));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_ADD_USER.formatted(2L), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to remove an user from a company")
  void removeUser() {
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(userService.getById(anyLong())).willReturn(company.getUsers().get(0));
    given(companyRepository.existsByUsers(any(User.class))).willReturn(true);
    given(companyRepository.save(any(Company.class))).willReturn(company);
    company.getUsers().add(
        User.builder()
            .id(2L)
            .name("Pedro")
            .surname("Perez")
            .password("123456778")
            .dni("12343445678")
            .email("ppe@emial.com")
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build()
    );
    //when
    Company companyUpdated = companyService.removeUser(1L, 1L);
    //then
    assertAll(
        () -> assertNotNull(companyUpdated),
        () -> assertNotNull(companyUpdated.getUsers()),
        () -> assertThat(companyUpdated.getUsers().size()).isEqualTo(1)
    );
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to remove an user who does not belong to a company")
  void failRemoveUser() {
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(userService.getById(anyLong())).willReturn(company.getUsers().get(0));
    given(companyRepository.existsByUsers(any(User.class))).willReturn(false);
    company.getUsers().add(
        User.builder()
            .id(2L)
            .name("Pedro")
            .surname("Perez")
            .password("123456778")
            .dni("12343445678")
            .email("ppe@emial.com")
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build()
    );
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.removeUser(1L, 1L));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_REMOVE_USER.formatted(1L), exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to get an error when trying to delete all users in a company")
  void  failRemoveAllUsers() {
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> companyService.removeUser(1L,1L));
    //then
    assertEquals(Messages.MESSAGE_COMPANY_WITHOUT_USERS,exception.getMessage());
  }

  @Test
  @DisplayName("Test CompanyService, test to change company's image")
  void changeImage() throws IOException {
    //given
    given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
    given(companyRepository.save(any(Company.class))).willReturn(company);
    willDoNothing().given(imageService).delete(anyLong());
    given(imageService.save(any(MultipartFile.class))).willReturn(company.getImage());
    willDoNothing().given(currentUserService).verifyCorrectUserInCompany(any(Company.class)));
    //when
    companyService.changeImage(1L, new MockMultipartFile("imageFile", "test.png", "image/png", "some image".getBytes()));
    //then
    verify(companyRepository, times(1)).save(company);
    verify(imageService, times(1)).delete(company.getImage().getId());
    verify(imageService, times(1)).save(any(MultipartFile.class));
  }
}
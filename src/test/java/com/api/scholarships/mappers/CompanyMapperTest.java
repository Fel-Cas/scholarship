package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CompanyDTOResponse;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

  private CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);

  @Test
  @DisplayName("Test companyToCompanyDTOResponse")
  void companyToCompanyDTOResponse() {
    //given
    Company company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(
            List.of(
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
            )
        )
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
    //when
    CompanyDTOResponse companyDTOResponse = companyMapper.companyToCompanyDTOResponse(company);
    //then
    assertAll(
        ()-> assertEquals(company.getId(), companyDTOResponse.getId()),
        ()-> assertEquals(company.getName(), companyDTOResponse.getName()),
        ()-> assertEquals(company.getAddress(), companyDTOResponse.getAddress()),
        ()-> assertEquals(company.getPhone(), companyDTOResponse.getPhone()),
        ()-> assertEquals(company.getEmail(), companyDTOResponse.getEmail()),
        ()-> assertEquals(company.getCreatedAt(), companyDTOResponse.getCreatedAt()),
        ()-> assertEquals(company.getUpdatedAt(), companyDTOResponse.getUpdatedAt()),
        ()-> assertEquals(company.getImage().getId(), companyDTOResponse.getImage().getId()),
        ()-> assertEquals(company.getImage().getName(), companyDTOResponse.getImage().getName()),
        ()-> assertEquals(company.getImage().getImageId(), companyDTOResponse.getImage().getImageId()),
        ()-> assertEquals(company.getImage().getUrl(), companyDTOResponse.getImage().getUrl()),
        ()->assertEquals(company.getUsers().size(), companyDTOResponse.getUsers().size())
    );
  }

  @Test
  @DisplayName("Test to switch from company list to companyDTOResponse list")
  void companyListToCompanyDTOResponseList() {
    //given
   Company company = Company.builder()
        .name("Company S.A")
        .address("Medellin,Antioquia")
        .phone("123456789")
        .email("email@emailcom")
        .id(1L)
        .users(
            List.of(
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
            )
        )
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
    List<Company> companies = List.of(company);
    //when
    List<CompanyDTOResponse> companiesDTOResponse = companyMapper.companyToCompanyDTOResponse(companies);
    //then
    assertAll(
        ()-> assertEquals(company.getId(), companiesDTOResponse.get(0).getId()),
        ()-> assertEquals(company.getName(), companiesDTOResponse.get(0).getName()),
        ()-> assertEquals(company.getAddress(), companiesDTOResponse.get(0).getAddress()),
        ()-> assertEquals(company.getPhone(), companiesDTOResponse.get(0).getPhone()),
        ()-> assertEquals(company.getEmail(), companiesDTOResponse.get(0).getEmail()),
        ()-> assertEquals(company.getCreatedAt(), companiesDTOResponse.get(0).getCreatedAt()),
        ()-> assertEquals(company.getUpdatedAt(), companiesDTOResponse.get(0).getUpdatedAt()),
        ()-> assertEquals(company.getImage().getId(), companiesDTOResponse.get(0).getImage().getId()),
        ()-> assertEquals(company.getImage().getName(), companiesDTOResponse.get(0).getImage().getName()),
        ()-> assertEquals(company.getImage().getImageId(), companiesDTOResponse.get(0).getImage().getImageId()),
        ()-> assertEquals(company.getImage().getUrl(), companiesDTOResponse.get(0).getImage().getUrl()),
        ()->assertEquals(company.getUsers().size(), companiesDTOResponse.get(0).getUsers().size())
    );
  }
}
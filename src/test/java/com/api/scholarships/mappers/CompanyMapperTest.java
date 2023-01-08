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
    List<User> users = new ArrayList<>();
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

    Company company = Company.builder()
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
}
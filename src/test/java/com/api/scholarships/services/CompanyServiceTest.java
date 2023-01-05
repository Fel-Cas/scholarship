package com.api.scholarships.services;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.repositories.CompanyRepository;
import com.api.scholarships.services.implementation.CompanyServiceImp;
import com.api.scholarships.services.interfaces.ImageService;
import com.api.scholarships.services.interfaces.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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
}
package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipServiceImp implements ScholarshipService {
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CareerService careerService;
  @Autowired
  private LanguageService languageService;
  @Autowired
  private StatusService service;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private CountryService countryService;
  @Autowired
  private CourseTypeService courseTypeService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private ScholarshipMapper scholarshipMapper;

  @Override
  public Scholarship create(ScholarshipDTO scholarshipDTO) {
    return null;
  }

  @Override
  public Scholarship getById(long id) {
    return null;
  }

  @Override
  public Scholarship update(ScholarshipUpdateDTO scholarshipUpdateDTO, long id) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

  @Override
  public ScholarshipResponse findAll() {
    return null;
  }
}

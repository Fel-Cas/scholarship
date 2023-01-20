package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.Scholarship;

import java.io.IOException;
import java.text.ParseException;

public interface ScholarshipService {
  public Scholarship create(ScholarshipDTO scholarshipDTO) throws IOException, ParseException;
  public Scholarship getById(long id);
  public Scholarship update(ScholarshipUpdateDTO scholarshipUpdateDTO, long id);
  public void delete(long id) throws IOException;
  public ScholarshipResponse findAll();
  public Scholarship changeCourseType(Long scholarshipId, Long courseTypeId);
  public Scholarship changeCountry(Long scholarshipId, Long countryId);
  public Scholarship changeStatus(Long scholarshipId, Long statusId);
  public Scholarship changeLanguage(Long scholarshipId, Long languageId);
  public Scholarship changeImage(Long scholarshipId);
  public Scholarship addCareer(Long scholarshipId, Long careerId);
  public Scholarship removeCareer(Long scholarshipId, Long careerId);
}

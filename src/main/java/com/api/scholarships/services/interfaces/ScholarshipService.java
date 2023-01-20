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
}

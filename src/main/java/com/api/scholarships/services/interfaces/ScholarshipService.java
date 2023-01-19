package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.Scholarship;

public interface ScholarshipService {
  public Scholarship create(ScholarshipDTO scholarshipDTO);
  public Scholarship getById(long id);
  public Scholarship update(ScholarshipUpdateDTO scholarshipUpdateDTO, long id);
  public void delete(long id);
  public ScholarshipResponse findAll();
}

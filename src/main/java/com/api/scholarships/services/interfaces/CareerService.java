package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;

public interface CareerService {
  public Career create(CareerDTO careerDTO);
  public Career findById(Long id);
  public Career findByName(String name);
  public CareerResponse findAll(int page, int size, String sort, String order);
  public void delete(Long id);
}

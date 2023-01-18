package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.repositories.CareerRepository;
import com.api.scholarships.services.interfaces.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerServiceImp implements CareerService {
  @Autowired
  private CareerRepository careerRepository;
  @Autowired
  private CareerMapper careerMapper;

  @Override
  public Career create(CareerDTO careerDTO) {
    return null;
  }

  @Override
  public Career findById(Long id) {
    return null;
  }

  @Override
  public Career findByName(String name) {
    return null;
  }

  @Override
  public CareerResponse findAll(int page, int size, String sort, String order) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}

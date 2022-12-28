package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.services.interfaces.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImp implements CompanyService {

  @Override
  public Company create(CompanyDTO companyDTO) {
    return null;
  }

  @Override
  public Company getOne(Long id) {
    return null;
  }

  @Override
  public CompanyResponse getAll(int page, int size, String sort, String order) {
    return null;
  }

  @Override
  public Company update(Long id, CompanyUpdateDTO companyDTO) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void addUser(Long id, Long userId) {

  }

  @Override
  public void removeUser(Long id, Long userId) {

  }

  @Override
  public void changeImage(Long id, Long imageId) {

  }
}

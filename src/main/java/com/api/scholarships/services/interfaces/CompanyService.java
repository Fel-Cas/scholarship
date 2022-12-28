package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;

public interface CompanyService {
  public Company create(CompanyDTO companyDTO);
  public Company getOne(Long id);
  public CompanyResponse getAll(int page, int size, String sort, String order);
  public Company update(Long id, CompanyUpdateDTO companyDTO);
  public void delete(Long id);
  public void addUser(Long id, Long userId);
  public void removeUser(Long id, Long userId);
  public void changeImage(Long id, Long imageId);
}

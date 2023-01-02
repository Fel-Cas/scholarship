package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyService {
  public Company create(CompanyDTO companyDTO) throws IOException;
  public Company getOne(Long id);
  public CompanyResponse getAll(int page, int size, String sort, String order);
  public Company update(Long id, CompanyUpdateDTO companyDTO);
  public void delete(Long id);
  public Company addUser(Long id, Long userId);
  public Company removeUser(Long id, Long userId);
  public void changeImage(Long id, MultipartFile image) throws IOException;
}

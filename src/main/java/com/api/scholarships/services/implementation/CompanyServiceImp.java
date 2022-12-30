package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.repositories.CompanyRepository;
import com.api.scholarships.services.interfaces.CloudService;
import com.api.scholarships.services.interfaces.CompanyService;
import com.api.scholarships.services.interfaces.ImageService;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private CompanyMapper companyMapper;
  @Autowired
  private ImageService imageService ;
  @Autowired
  private UserService userService;

  @Override
  public Company create(CompanyDTO companyDTO) throws IOException {
    validateUniqueInformationCreateCompany(companyDTO);
    List<User> users = loadUsers(companyDTO.getUsers());
    Image imageCreated=imageService.save(companyDTO.getImage());
    return companyRepository.save(
        Company.builder()
            .name(companyDTO.getName().toUpperCase())
            .address(companyDTO.getAddress().toUpperCase())
            .email(companyDTO.getEmail())
            .phone(companyDTO.getPhone())
            .users(users)
            .image(imageCreated)
            .build());
  }

  @Override
  public Company getOne(Long id) {
    Optional<Company> companyFound=companyRepository.findById(id);
    if(companyFound.isEmpty()){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_NOT_FOUND.formatted(id));
    }
    return companyFound.get();
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

  protected void validateUniqueInformationCreateCompany(CompanyDTO companyDTO){
    if(companyRepository.existsByName(companyDTO.getName())){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_NAME.formatted(companyDTO.getName()));
    }
    if (companyRepository.existsByEmail(companyDTO.getEmail())){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL.formatted(companyDTO.getEmail()));
    }
    if(companyRepository.existsByPhone(companyDTO.getPhone())){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_PHONE.formatted(companyDTO.getPhone()));
    }
  }

  protected List<User> loadUsers(List<Long> usersId){
    return usersId.stream().map(userService::getById).toList();
  }
}

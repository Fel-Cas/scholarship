package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CompanyDTO;
import com.api.scholarships.dtos.CompanyResponse;
import com.api.scholarships.dtos.CompanyUpdateDTO;
import com.api.scholarships.entities.Company;
import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CompanyMapper;
import com.api.scholarships.repositories.CompanyRepository;
import com.api.scholarships.services.interfaces.CompanyService;
import com.api.scholarships.services.interfaces.ImageService;
import com.api.scholarships.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
      throw new NotFoundException(Messages.MESSAGE_COMPANY_NOT_FOUND.formatted(id));
    }
    return companyFound.get();
  }

  @Override
  public CompanyResponse getAll(int page, int size, String sort, String order) {
    Sort sortDirection = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sort).ascending() : Sort.by(sort).descending();
    Page<Company> companiesFound=companyRepository.findAll(PageRequest.of(page, size, sortDirection));
    return CompanyResponse.builder()
        .content(companyMapper.companyToCompanyDTOResponse(companiesFound.getContent()))
        .totalPages(companiesFound.getTotalPages())
        .totalElements(companiesFound.getTotalElements())
        .lastOne(companiesFound.isLast())
        .numberPage(companiesFound.getNumber())
        .sizePage(companiesFound.getSize())
        .build();
  }

  @Override
  public Company update(Long id, CompanyUpdateDTO companyDTO) {
    Company companyFound=getOne(id);
    validateUniqueInformationUpdateCompany(companyDTO,id);
    updateCompanyData(companyFound,companyDTO);
    return companyRepository.save(companyFound);
  }

  @Override
  public void delete(Long id) {
    Company companyFound=getOne(id);
    companyRepository.delete(companyFound);
  }

  @Override
  public Company addUser(Long id, Long userId) {
    Company companyFound=getOne(id);
    User userFound=userService.getById(userId);
    if(companyRepository.existsByUsers(userFound)){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_ADD_USER.formatted(userId));
    }
    companyFound.getUsers().add(userFound);
    return companyRepository.save(companyFound);
  }

  @Override
  public Company removeUser(Long id, Long userId) {
    Company companyFound=getOne(id);
    User userFound=userService.getById(userId);
    if(!companyRepository.existsByUsers(userFound)){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_REMOVE_USER.formatted(userId));
    }
    companyFound.getUsers().remove(userFound);
    return companyRepository.save(companyFound);
  }

  @Override
  public void changeImage(Long id, MultipartFile image) throws IOException {
    Company companyFound=getOne(id);
    imageService.delete(companyFound.getImage().getId());
    Image imageCreated=imageService.save(image);
    companyFound.setImage(imageCreated);
    companyRepository.save(companyFound);
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

  protected void validateUniqueInformationUpdateCompany(CompanyUpdateDTO companyDTO,Long id){
    if(companyRepository.existsByNameAndIdNot(companyDTO.getName(),id)){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_NAME.formatted(companyDTO.getName()));
    }
    if (companyRepository.existsByEmailAndIdNot(companyDTO.getEmail(),id)){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL.formatted(companyDTO.getEmail()));
    }
    if(companyRepository.existsByPhoneAndIdNot(companyDTO.getPhone(),id)){
      throw new BadRequestException(Messages.MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_PHONE.formatted(companyDTO.getPhone()));
    }
  }

  protected void updateCompanyData(Company companyFound,CompanyUpdateDTO companyDTO){
    companyFound.setName(companyDTO.getName().toUpperCase());
    companyFound.setAddress(companyDTO.getAddress().toUpperCase());
    companyFound.setEmail(companyDTO.getEmail());
    companyFound.setPhone(companyDTO.getPhone());
  }

  protected List<User> loadUsers(List<Long> usersId){
    return usersId.stream().map(userId->{
      User userFound=userService.getById(userId);
      if(companyRepository.existsByUsers(userFound)){
        throw new BadRequestException(Messages.MESSAGE_COMPANY_ADD_USER.formatted(userId));
      }
      return userFound;
    }).toList();
  }
}

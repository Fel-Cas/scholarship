package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CareerDTO;
import com.api.scholarships.dtos.CareerResponse;
import com.api.scholarships.entities.Career;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CareerMapper;
import com.api.scholarships.repositories.CareerRepository;
import com.api.scholarships.services.interfaces.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CareerServiceImp implements CareerService {
  @Autowired
  private CareerRepository careerRepository;
  @Autowired
  private CareerMapper careerMapper;

  @Override
  public Career create(CareerDTO careerDTO) {
    if(careerRepository.existsByCareerName(careerDTO.getCareerName())){
      throw new BadRequestException(Messages.MESSAGE_CREATE_CAREER_WITH_WRONG_NAME.formatted(careerDTO.getCareerName()));
    }
    return careerRepository.save(
        Career.builder()
            .careerName(careerDTO.getCareerName().toUpperCase())
            .build()
    );
  }

  @Override
  public Career findById(Long id) {
    Optional<Career> careerFound = careerRepository.findById(id);
    if(careerFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_CAREER_NOT_FOUND.formatted(id));
    }
    return careerFound.get();
  }

  @Override
  public Career findByName(String name) {
    Optional<Career> careerFound = careerRepository.findByCareerName(name);
    if(careerFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_CAREER_NOT_FOUND_BY_NAME.formatted(name));
    }
    return careerFound.get();
  }

  @Override
  public CareerResponse findAll(int page, int size, String sort, String order) {
    Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sort).ascending() : Sort.by(sort).descending();
    Page<Career> careersFound=careerRepository.findAll(PageRequest.of(page,size,sortDirection));
    return CareerResponse.builder()
        .content(careerMapper.careerListToCareerDTOResponseList(careersFound.getContent()))
        .numberPage(careersFound.getNumber())
        .sizePage(careersFound.getSize())
        .totalElements(careersFound.getTotalElements())
        .totalPages(careersFound.getTotalPages())
        .lastOne(careersFound.isLast())
        .build();
  }

  @Override
  public void delete(Long id) {
    Career careerFound=findById(id);
    careerRepository.delete(careerFound);
  }
}

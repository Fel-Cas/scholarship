package com.api.scholarships.mappers;

import com.api.scholarships.dtos.CareerDTOResponse;
import com.api.scholarships.entities.Career;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerMapper {
  public CareerDTOResponse careerToCareerDTOResponse(Career career);
  public List<CareerDTOResponse> careerListToCareerDTOResponseList(Iterable<Career> careers);
}

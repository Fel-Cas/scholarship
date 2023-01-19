package com.api.scholarships.mappers;

import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.entities.Scholarship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {
  public ScholarshipDTOResponse scholarshipToScholarshipDTOResponse(Scholarship scholarship);
  public List<ScholarshipDTOResponse> scholarshipListToScholarshipDTOResponseList(Iterable<Scholarship> scholarshipList);
}

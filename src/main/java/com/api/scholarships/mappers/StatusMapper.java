package com.api.scholarships.mappers;

import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.entities.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
  public StatusDTO statusToStatusDTO(Status status);
  public List<StatusDTO> statusToStatusDTO(Iterable<Status> states);
}

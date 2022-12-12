package com.api.scholarships.mappers;

import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeDTOResponse;
import com.api.scholarships.entities.LegalRepresentative;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LegalRepresentativeMapper {
  LegalRepresentativeDTOResponse legalRepresentativeToLegalRepresentativeDTOResponse(LegalRepresentative legalRepresentative);
  List<LegalRepresentativeDTOResponse> legalRepresentativeToLegalRepresentativeDTOResponse(Iterable<LegalRepresentative> legalRepresentative);
  LegalRepresentative legalRepresentativeDTOToLegalRepresentative(LegalRepresentativeDTO legalRepresentativeDTO);
}

package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeResponse;
import com.api.scholarships.entities.LegalRepresentative;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.LegalRepresentativeMapper;
import com.api.scholarships.repositories.LegalRepresentativeRepository;
import com.api.scholarships.services.interfaces.LegalRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalRepresentativeServiceImp implements LegalRepresentativeService {

  @Autowired
  private LegalRepresentativeRepository legalRepresentativeRepository;
  @Autowired
  private LegalRepresentativeMapper legalRepresentativeMapper;

  @Override
  public LegalRepresentative save(LegalRepresentativeDTO legalRepresentative) {
    if(legalRepresentativeRepository.existsByEmail(legalRepresentative.getEmail())) throw new BadRequestException(Messages.MESSAGE_LEGAL_REPRESENTATIVE_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL);
    if(legalRepresentativeRepository.existsByDni(legalRepresentative.getDni())) throw new BadRequestException(Messages.MESSAGE_LEGAL_REPRESENTATIVE_BAD_REQUEST_CREATE_WITH_WRONG_DNI);
    return legalRepresentativeRepository.save(legalRepresentativeMapper.legalRepresentativeDTOToLegalRepresentative(legalRepresentative));
  }

  @Override
  public LegalRepresentativeResponse getAllLegalRepresentatives(int page, int size) {
    return null;
  }

  @Override
  public LegalRepresentativeResponse getLegalRepresentativeById(Long id) {
    return null;
  }

  @Override
  public LegalRepresentative getLegalRepresentativeByEmail(String email) {
    return null;
  }

  @Override
  public LegalRepresentative updateLegalRepresentative(Long id, LegalRepresentativeDTO legalRepresentative) {
    return null;
  }

  @Override
  public void deleteLegalRepresentative(Long id) {

  }
}

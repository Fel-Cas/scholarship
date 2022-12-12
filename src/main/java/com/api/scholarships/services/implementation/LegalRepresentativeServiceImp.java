package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeResponse;
import com.api.scholarships.entities.LegalRepresentative;
import com.api.scholarships.services.interfaces.LegalRepresentativeService;
import org.springframework.stereotype.Service;

@Service
public class LegalRepresentativeServiceImp implements LegalRepresentativeService {

  @Override
  public LegalRepresentative save(LegalRepresentativeDTO legalRepresentative) {
    return null;
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

package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.LegalRepresentativeDTO;
import com.api.scholarships.dtos.LegalRepresentativeResponse;
import com.api.scholarships.entities.LegalRepresentative;

public interface LegalRepresentativeService {
  public LegalRepresentative save(LegalRepresentativeDTO legalRepresentative);
  public LegalRepresentativeResponse getAllLegalRepresentatives(int page, int size,String sort, String order);
  public LegalRepresentative getLegalRepresentativeById(Long id);
  public LegalRepresentative getLegalRepresentativeByDNI(String dni);
  public LegalRepresentative updateLegalRepresentative(Long id, LegalRepresentativeDTO legalRepresentative);
  public void deleteLegalRepresentative(Long id);
}

package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.LanguageResponse;
import com.api.scholarships.entities.Language;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.LanguageMapper;
import com.api.scholarships.repositories.LanguageRepository;
import com.api.scholarships.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageServiceImp implements LanguageService {

  @Autowired
  private LanguageRepository languageRepository;
  @Autowired
  private LanguageMapper languageMapper;

  @Override
  public Language findById(Long id) {
    Optional<Language> languageFound=languageRepository.findById(id);
    if(languageFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_LANGUAGE_NOT_FOUND.formatted(id));
    }
    return languageFound.get();
  }

  @Override
  public Language findByName(String name) {
    Optional<Language> languageFound=languageRepository.findByLanguageName(name);
    if(languageFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_LANGUAGE_NOT_FOUND_BY_NAME.formatted(name));
    }
    return languageFound.get();
  }
  @Override
  public LanguageResponse findAll(int page, int size, String sort, String order) {
    Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sort).ascending():Sort.by(sort).descending();
    Page<Language> languagesFound=languageRepository.findAll(PageRequest.of(page,size,sortDirection));
    return LanguageResponse.builder()
        .content(languageMapper.languageToLaguageDTO(languagesFound.getContent()))
        .numberPage(languagesFound.getNumber())
        .sizePage(languagesFound.getSize())
        .totalElements(languagesFound.getTotalElements())
        .totalPages(languagesFound.getTotalPages())
        .lastOne(languagesFound.isLast())
        .build();
  }
}

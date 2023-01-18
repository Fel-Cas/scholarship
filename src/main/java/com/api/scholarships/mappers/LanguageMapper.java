package com.api.scholarships.mappers;

import com.api.scholarships.dtos.LanguageDTO;
import com.api.scholarships.entities.Language;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
  public LanguageDTO languageToLaguageDTO(Language language);
  public List<LanguageDTO> languageToLaguageDTO(Iterable<Language> language);
}

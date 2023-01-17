package com.api.scholarships.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class LanguageDTO {
  private Long id;
  private String languageName;
}

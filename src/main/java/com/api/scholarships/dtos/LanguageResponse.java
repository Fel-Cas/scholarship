package com.api.scholarships.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class LanguageResponse {
  private List<LanguageDTO> content;
  private int numberPage;
  private int sizePage;
  private Long totalElements;
  private int totalPages;
  private  boolean lastOne;
}

package com.api.scholarships.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Builder
public class LegalRepresentativeResponse{
  private List<LegalRepresentativeDTOResponse> content;
  private int numberPage;
  private int sizePage;
  private Long totalElements;
  private int totalPages;
  private  boolean lastOne;
}

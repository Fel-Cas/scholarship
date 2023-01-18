package com.api.scholarships.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class CareerDTOResponse {
  private Long id;
  private String careerName;
}

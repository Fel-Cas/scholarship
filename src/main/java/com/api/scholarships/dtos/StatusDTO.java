package com.api.scholarships.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class StatusDTO {
  private Long id;
  private String statusName;
}

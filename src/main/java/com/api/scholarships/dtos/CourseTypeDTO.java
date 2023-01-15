package com.api.scholarships.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
public class CourseTypeDTO {
  private Long id;
  private String courseType;
}

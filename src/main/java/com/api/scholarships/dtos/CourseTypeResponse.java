package com.api.scholarships.dtos;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
public class CourseTypeResponse {
  private List<CourseTypeDTO> content;
  private int numberPage;
  private int sizePage;
  private Long totalElements;
  private int totalPages;
  private  boolean lastOne;
}

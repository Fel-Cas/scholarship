package com.api.scholarships.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class RoleResponse {
  private List<RoleDTO> content;
  private int numberPage;
  private int sizePage;
  private Long totalElements;
  private int totalPages;
  private  boolean lastOne;
}

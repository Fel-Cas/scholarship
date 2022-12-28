package com.api.scholarships.dtos;

import java.util.List;

public class CompanyResponse {
  private List<CompanyDTOResponse> content;
  private int numberPage;
  private int sizePage;
  private Long totalElements;
  private int totalPages;
  private  boolean lastOne;
}

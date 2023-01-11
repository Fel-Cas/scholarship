package com.api.scholarships.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CountryDTOResponse {
  private Long id;
  private String countryName;
  private String abbreviation;

  @Override
  public String toString() {
      return "CountryDTOResponse{" +
              "id=" + id +
              ", countryName='" + countryName + '\'' +
              ", abbreviation='" + abbreviation + '\'' +
              '}';
  }
}

package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CountryDTO {
  @NotNull(message = Messages.MESSAGE_COUNTRY_REQUIRED)
  @Size(min = 3, max = 50, message = Messages.MESSAGE_COUNTRY_SIZE)
  private String countryName;
  @NotNull(message = Messages.MESSAGE_COUNTRY_ABBREVIATION_REQUIRED)
  @Size(min = 2, max = 3, message = Messages.MESSAGE_COUNTRY_ABBREVIATION_SIZE)
  private String abbreviation;
}

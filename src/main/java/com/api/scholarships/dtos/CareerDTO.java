package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CareerDTO {
  @NotNull(message = Messages.MESSAGE_CAREER_NAME_REQUIRED)
  @Size(min = 5, max = 255, message = Messages.MESSAGE_CAREER_SIZE)
  private String careerName;
}

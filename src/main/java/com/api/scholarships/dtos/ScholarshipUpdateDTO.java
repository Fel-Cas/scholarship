package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ScholarshipUpdateDTO {
  @NotEmpty
  @Size(min=10)
  private String title;
  @NotEmpty(message = Messages.MESSAGE_DESCRIPTION_REQUIRED)
  @Size(min=200, message = Messages.MESSAGE_DESCRIPTION_SIZE)
  private String description;
  @NotEmpty(message = Messages.MESSAGE_START_DATE_REQUIRED)
  private LocalDateTime startDate;
  @NotEmpty(message = Messages.MESSAGE_END_DATE_REQUIRED)
  private LocalDateTime finishDate;
  @NotEmpty(message = Messages.MESSAGE_URL_REQUIRED)
  @URL(message = Messages.MESSAGE_URL_VALID)
  private String link;
}

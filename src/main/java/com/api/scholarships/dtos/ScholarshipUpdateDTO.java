package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.bridge.Message;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ScholarshipUpdateDTO {
  @NotEmpty(message=Messages.MESSAGE_TITLE_IS_REQUIRED)
  @Size(min=10, message = Messages.MESSAGE_TITLE_SIZE)
  private String title;
  @NotEmpty(message = Messages.MESSAGE_DESCRIPTION_REQUIRED)
  @Size(min=200, message = Messages.MESSAGE_DESCRIPTION_SIZE)
  private String description;
  @NotNull(message = Messages.MESSAGE_START_DATE_REQUIRED)
  @DateTimeFormat(pattern =  "yyyy-MM-dd")
  private Date startDate;
  @NotNull(message = Messages.MESSAGE_END_DATE_REQUIRED)
  @DateTimeFormat(pattern =  "yyyy-MM-dd")
  private Date finishDate;
  @NotEmpty(message = Messages.MESSAGE_URL_REQUIRED)
  @URL(message = Messages.MESSAGE_URL_VALID)
  private String link;
}

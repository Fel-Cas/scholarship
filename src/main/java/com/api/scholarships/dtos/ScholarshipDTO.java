package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.entities.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ScholarshipDTO {
  @NotEmpty(message =Messages.MESSAGE_DESCRIPTION_REQUIRED)
  @Size(min=200, message = Messages.MESSAGE_DESCRIPTION_SIZE)
  private String description;
  @NotEmpty(message = Messages.MESSAGE_START_DATE_REQUIRED)
  private LocalDateTime startDate;
  @NotEmpty(message = Messages.MESSAGE_END_DATE_REQUIRED)
  private LocalDateTime finishDate;
  @NotEmpty(message = Messages.MESSAGE_URL_REQUIRED)
  @URL(message = Messages.MESSAGE_URL_VALID)
  private String link;
  @NotEmpty(message = Messages.MESSAGE_COURSE_TYPE_REQUIRED)
  @Size(min = 8, max = 80, message = Messages.MESSAGE_COURSE_TYPE_SIZE)
  private String courseType;
  @NotEmpty(message=Messages.MESSAGE_COUNTRY_REQUIRED)
  @Size(min=3, max=50, message = Messages.MESSAGE_COUNTRY_SIZE)
  private String country;
  @NotEmpty(message=Messages.MESSAGE_LANGUAGE_REQUIRED)
  @Size(min=5, max=30, message = Messages.MESSAGE_LANGUAGE_SIZE)
  private String language;
  @NotNull(message = Messages.MESSAGE_IMAGE_REQUIRED)
  private MultipartFile image;
  @NotEmpty(message = Messages.MESSAGE_COMPANY_REQUIRED)
  @Positive
  private Long company;
  @NotEmpty(message = Messages.MESSAGE_CARRERS_ID_REQUIRED)
  @Size(min=1,message =Messages.MESSAGE_CARRERS_ID_SIZE)
  private List<String> careers;

}

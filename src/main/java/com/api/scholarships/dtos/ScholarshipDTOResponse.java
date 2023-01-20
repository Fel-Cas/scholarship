package com.api.scholarships.dtos;

import com.api.scholarships.entities.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ScholarshipDTOResponse {
  private Long id;
  private String title;
  private String description;
  private Date startDate;
  private Date finishDate;
  private String link;
  private CourseType courseType;
  private Country country;
  private Status status;
  private Language language;
  private Image image;
  private Company company;
  private List<Career> careers;
  private Instant createdAt;
  private Instant updatedAt;

}

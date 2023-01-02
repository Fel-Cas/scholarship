package com.api.scholarships.dtos;

import com.api.scholarships.entities.Image;
import com.api.scholarships.entities.User;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CompanyDTOResponse {
  private Long id;
  private String name;
  private String address;
  private String email;
  private String phone;
  private Image image;
  private List<User> users;
  private Instant createdAt;
  private Instant updatedAt;
}

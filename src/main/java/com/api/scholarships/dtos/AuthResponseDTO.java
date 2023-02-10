package com.api.scholarships.dtos;

import com.api.scholarships.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthResponseDTO {
  private UserDTOResponse user;
  private String token;

}

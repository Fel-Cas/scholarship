package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class CompanyUpdateDTO {
  @NotEmpty(message = Messages.MESSAGE_NAME_REQUIRED)
  @Size(min = 2, max = 50, message = Messages.MESSAGE_NAME_SIZE)
  private String name;
  @NotEmpty(message = Messages.MESSAGE_ADDRESS_REQUIRED)
  @Size(min = 8, max = 50, message = Messages.MESSAGE_ADDRESS_SIZE)
  private String address;
  @NotEmpty(message = Messages.MESSAGE_PHONE_REQUIRED)
  @Size(min = 8, max = 15, message = Messages.MESSAGE_PHONE_SIZE)
  private String phone;
  @NotEmpty(message = Messages.MESSAGE_EMAIL_REQUIRED)
  @Size(min = 11, max = 50, message = Messages.MESSAGE_EMAIL_SIZE)
  private String email;
}

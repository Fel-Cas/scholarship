package com.api.scholarships.dtos;

import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Builder
public class UserUpdateDTO {
  @NotNull(message = Messages.MESSAGE_NAME_REQUIRED)
  @Size(min = 2, max = 50, message = Messages.MESSAGE_NAME_SIZE)
  String name;
  @NotNull(message = Messages.MESSAGE_SURNAME_REQUIRED)
  @Size(min = 2, max = 50, message = Messages.MESSAGE_SURNAME_SIZE)
  String surname;
  @NotNull(message = Messages.MESSAGE_EMAIL_REQUIRED)
  @Email(message = Messages.MESSAGE_EMAIL_VALID)
  @Size(min = 11, max = 50, message = Messages.MESSAGE_EMAIL_SIZE)
  String email;
  @NotNull(message = Messages.MESSAGE_DNI_REQUIRED)
  @Size(min = 10, max = 15, message = Messages.MESSAGE_DNI_SIZE)
  String dni;
}

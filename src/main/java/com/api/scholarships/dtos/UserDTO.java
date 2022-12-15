package com.api.scholarships.dtos;



import com.api.scholarships.constants.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class UserDTO {
    @NotNull(message = Messages.MESSAGE_NAME_REQUIRED)
    @Size(min = 2, max = 50, message = Messages.MESSAGE_NAME_SIZE)
    private String name;
    @NotNull(message = Messages.MESSAGE_SURNAME_REQUIRED)
    @Size(min = 2, max = 50, message = Messages.MESSAGE_SURNAME_SIZE)
    private String surname;
    @NotNull(message = Messages.MESSAGE_EMAIL_REQUIRED)
    @Email(message = Messages.MESSAGE_EMAIL_VALID)
    @Size(min = 11, max = 50, message = Messages.MESSAGE_EMAIL_SIZE)
    private String email;
    @NotNull(message = Messages.MESSAGE_DNI_REQUIRED)
    @Size(min = 10, max = 15, message = Messages.MESSAGE_DNI_SIZE)
    private String dni;
    @NotNull(message = Messages.MESSAGE_PASSWORD_REQUIRED)
    @Size(min = 8, max = 50, message = Messages.MESSAGE_PASSWORD_SIZE)
    private String password;
    @NotNull(message = Messages.MESSAGE_ROLE_REQUIRED)
    @Size(min=4, max=50, message = Messages.MESSAGE_ROLE_SIZE)
    private String role;

    @Override
    public String toString() {
        return "UserDTO{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", dni='" + dni + '\'' +
            ", password='" + password + '\'' +
            ", role='" + role + '\'' +
            '}';
    }
}

package com.api.scholarships.dtos;

import com.api.scholarships.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UserDTOResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("dni")
    private String dni;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;


    @Override
    public String toString() {
        return "UserDTOResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", dni='" + dni + '\'' +
            ", role=" + role.toString() +
            ", createdAt='" + createdAt + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            '}';
    }
}

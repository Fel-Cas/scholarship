package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LegalRepresentative{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "dni", nullable = false)
    private String dni;
    @Column(name = "password", nullable = false)
    private String password;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @Override
    public String toString() {
        return "LegalRepresentative {id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", dni=" + dni + ", password=" + password + "}";
    }
}
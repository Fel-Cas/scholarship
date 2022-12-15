package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
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
    //TODO: add created_at and updated_at correctly
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String toString() {
        return "LegalRepresentative {id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", dni=" + dni + ", password=" + password + "}";
    }
}
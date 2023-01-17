package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true,nullable = false,name = "language_name")
  private String languageName;
}

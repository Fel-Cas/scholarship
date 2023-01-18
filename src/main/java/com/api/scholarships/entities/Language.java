package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Language implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true,nullable = false,name = "language_name")
  private String languageName;
}

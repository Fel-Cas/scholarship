package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "careers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Career {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String careerName;
}

package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "careers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Career implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String careerName;
}

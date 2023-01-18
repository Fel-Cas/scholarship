package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Country implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "country_name", nullable = false)
  private String countryName;
  @Column(name = "abbreviation", nullable = false)
  private String abbreviation;
}

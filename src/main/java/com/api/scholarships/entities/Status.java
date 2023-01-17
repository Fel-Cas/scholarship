package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Status {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false, name = "status_name")
  private String statusName;
}

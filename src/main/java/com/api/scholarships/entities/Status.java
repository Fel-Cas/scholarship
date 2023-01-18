package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity()
@Table(name = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Status implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false, name = "status_name")
  private String statusName;
}

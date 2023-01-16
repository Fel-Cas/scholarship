package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "course_type", unique = true, nullable = false)
  private String courseType;
}

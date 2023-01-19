package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "scholarships")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Scholarship implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false,columnDefinition = "TEXT")
  private String description;
  @Column(nullable = false, name = "start_date")
  private LocalDateTime startDate;
  @Column(nullable = false, name = "finish_date")
  private LocalDateTime finishDate;
  @Column(name = "link")
  private String link;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_type_id")
  private CourseType courseType;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="country_id")
  private Country country;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "status_id")
  private Status status;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="language_id")
  private Language language;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "image_id",referencedColumnName = "id")
  private Image image;
  @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "scholarship_company",
      joinColumns = {@JoinColumn(name = "scholarship_id")},
      inverseJoinColumns = {@JoinColumn(name = "company_id")} )
  private List<Company> companies;
  @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "scholarship_career",
      joinColumns =  {@JoinColumn(name = "scholarship_id")},
      inverseJoinColumns =  {@JoinColumn(name = "career_id")} )
  private List<Career> careers;
  @CreationTimestamp
  private Instant createdAt;
  @UpdateTimestamp
  private Instant updatedAt;
}

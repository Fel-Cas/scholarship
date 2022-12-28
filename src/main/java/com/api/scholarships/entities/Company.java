package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Data
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "full_name")
  private String name;
  @Column(name = "address")
  private String address;
  @Column(name = "phone")
  private String phone;
  @Column(name = "email")
  private String email;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "image_id",referencedColumnName = "id")
  private Image image;
  @OneToMany(fetch = FetchType.EAGER,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
  @JoinColumn(name = "company_id")
  private List<User> users;
  @CreationTimestamp
  private Instant createdAt;
  @UpdateTimestamp
  private Instant updatedAt;

}

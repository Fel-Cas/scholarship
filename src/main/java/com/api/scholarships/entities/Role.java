package com.api.scholarships.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name_role")
  private String nameRole;
  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", nameRole='" + nameRole + '\'' +
        '}';
  }
}

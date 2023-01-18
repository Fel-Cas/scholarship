package com.api.scholarships.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name_role")
  private String nameRole;
  @OneToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
  @JoinColumn(name = "role_id")
  @JsonIgnore
  private List<User> roles;

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", nameRole='" + nameRole + '\'' +
        '}';
  }
}

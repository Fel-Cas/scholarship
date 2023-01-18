package com.api.scholarships.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Image implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "image_url")
  private String url;
  @Column(name = "image_name")
  private String name;
  @Column(name = "image_id")
  private String imageId;
  @OneToOne(mappedBy = "image",fetch = FetchType.LAZY)
  @JsonIgnore
  private Company company;

  @Override
  public String toString() {
    return "Image{" +
        "id=" + id +
        ", url='" + url + '\'' +
        ", name='" + name + '\'' +
        ", imageId='" + imageId + '\'' +
        '}';
  }
}

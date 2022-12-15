package com.api.scholarships.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "image_url")
  private String url;
  @Column(name = "image_name")
  private String name;
  @Column(name = "image_id")
  private String imageId;
  @OneToOne(mappedBy = "image")
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

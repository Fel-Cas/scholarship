package com.api.scholarships.repositories;

import com.api.scholarships.entities.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ImageRepositoryTest {

  @Autowired
  private ImageRepository imageRepository;
  private Image image;

  @BeforeEach
  void init(){
    image = Image.builder()
        .id(1L)
        .name("image1")
        .imageId("image1")
        .url("url")
        .build();
  }

  @Test
  void create(){
    //given
    //when
    Image imageSaved = imageRepository.save(image);
    //then
    assertNotNull(imageSaved);
    assertThat(imageSaved.getId()).isGreaterThan(0);
    assertEquals(imageSaved.getName(),image.getName());
    assertEquals(imageSaved.getImageId(),image.getImageId());
    assertEquals(imageSaved.getUrl(),image.getUrl());
  }

  @Test
  void findAll(){
    //given
    imageRepository.save(image);
    //when
    List<Image> imagesFound = imageRepository.findAll();
    //then
    assertNotNull(imagesFound);
    assertThat(imagesFound.size()).isGreaterThan(0);
  }

}
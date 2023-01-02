package com.api.scholarships.repositories;

import com.api.scholarships.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ImageRepositoryTest {

  @Autowired
  private ImageRepository imageRepository;
  private Image image;
}
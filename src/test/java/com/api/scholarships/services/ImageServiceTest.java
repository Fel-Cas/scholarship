package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.entities.Image;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.repositories.ImageRepository;
import com.api.scholarships.services.implementation.ImageServiceImp;
import com.api.scholarships.services.interfaces.CloudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ImageServiceTest {
  @Mock
  private CloudService cloudService;
  @Mock
  private ImageRepository imageRepository;
  @InjectMocks
  private ImageServiceImp imageService;
  private Image image;

  @BeforeEach()
  void setUp() {
    image = Image.builder()
      .id(1L)
      .name("test")
      .url("localhost:8080")
      .imageId("imageId")
      .build();
  }
  @Test
  @DisplayName("Test ImageService, test to save an image")
  void save() throws IOException {
    //given
    FileInputStream inputfile = new FileInputStream("src/test/images/test.png");
    MockMultipartFile imageFile = new MockMultipartFile("imageFile", "test.png", "image/png", inputfile);

    Map<String,String> response=Map.of("url",image.getUrl(),"public_id",image.getImageId(),"original_filename",image.getName());
    given(cloudService.upload(imageFile)).willReturn(response);

    given(imageRepository.save(any(Image.class))).willReturn(image);

    //when
    Image imageSaved = imageService.save(imageFile);
    //then
    assertNotNull(imageSaved);
    assertEquals(imageSaved.getName(),image.getName());
    assertEquals(imageSaved.getUrl(),image.getUrl());
    assertEquals(imageSaved.getImageId(),image.getImageId());
  }

  @Test
  @DisplayName("Test ImageService, test to verify exception when saving an image")
  void failSave() throws IOException {
    //given
    MockMultipartFile imageFile = new MockMultipartFile("imageFile", "test.png", "image/png", new byte[0]);
    //when
    BadRequestException exception = assertThrows(BadRequestException.class, () -> imageService.save(imageFile));
    //then
    assertEquals(Messages.MESSAGE_IMAGE_NOT_VALID, exception.getMessage());
  }
}
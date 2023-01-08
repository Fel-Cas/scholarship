package com.api.scholarships.services;

import com.api.scholarships.services.implementation.CloudinaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CloudinaryServiceTest {

  @InjectMocks
  private CloudinaryService cloudinaryService;
  private MockMultipartFile image;

  @BeforeEach
  void setUp() throws IOException {
    FileInputStream inputfile = new FileInputStream("src/test/images/test.png");
    image= new MockMultipartFile("imageFile", "test.png", "image/png", inputfile);
  }

  @Test
  @DisplayName("Test CloudinaryService, test to upload an image")
  void upload() throws IOException {
    //when
    Map response=cloudinaryService.upload(image);
    //then
    assertNotNull(response);
    assertEquals("test",response.get("original_filename"));
    assertNotNull(response.get("url"));
    assertNotNull(response.get("public_id"));
  }
}
package com.api.scholarships.services;

import com.api.scholarships.entities.Image;
import com.api.scholarships.repositories.ImageRepository;
import com.api.scholarships.services.implementation.ImageServiceImp;
import com.api.scholarships.services.interfaces.CloudService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

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

}
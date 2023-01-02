package com.api.scholarships.services.interfaces;

import com.api.scholarships.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
  public Image save(MultipartFile image) throws IOException;
  public void delete(Long id) throws IOException;
}

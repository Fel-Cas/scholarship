package com.api.scholarships.services.interfaces;

import com.api.scholarships.entities.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
  public Image save(MultipartFile image);
  public void delete(Long id);
}

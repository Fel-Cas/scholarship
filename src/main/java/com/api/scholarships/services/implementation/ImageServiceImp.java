package com.api.scholarships.services.implementation;

import com.api.scholarships.entities.Image;
import com.api.scholarships.services.interfaces.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImp implements ImageService {

  @Override
  public Image save(MultipartFile image) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}

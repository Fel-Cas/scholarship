package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.entities.Image;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.repositories.ImageRepository;
import com.api.scholarships.services.interfaces.CloudService;
import com.api.scholarships.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class ImageServiceImp implements ImageService {

  @Autowired
  private CloudService cloudinaryService;
  @Autowired
  private ImageRepository imageRepository;

  @Override
  public Image save(MultipartFile image) throws IOException {
    Map result = this.cloudinaryService.upload(image);
    return imageRepository.save(
        Image.builder()
            .name((String) result.get("original_filename"))
            .imageId((String) result.get("public_id"))
            .url((String) result.get("url"))
            .build());
  }

  @Override
  public void delete(Long id) {
    Optional<Image> imageFound = imageRepository.findById(id);
    if(imageFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_IMAGE_NOT_FOUND.formatted(id));
    }
    imageRepository.delete(imageFound.get());
  }
}

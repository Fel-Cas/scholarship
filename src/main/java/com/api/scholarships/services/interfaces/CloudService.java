package com.api.scholarships.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface CloudService {

  public Map upload(MultipartFile multipartFile);
  public Map delete(String id);
  public File convert(MultipartFile multipartFile);
}

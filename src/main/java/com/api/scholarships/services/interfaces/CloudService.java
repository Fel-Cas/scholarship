package com.api.scholarships.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudService {

  public Map upload(MultipartFile multipartFile) throws IOException;
  public Map delete(String id) throws IOException;
}

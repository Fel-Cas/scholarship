package com.api.scholarships.services.implementation;

import com.api.scholarships.services.interfaces.CloudService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public class CloudinaryService implements CloudService {

    @Override
    public Map upload(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public Map delete(String id) {
        return null;
    }

    @Override
    public File convert(MultipartFile multipartFile) {
        return null;
    }
}


package com.api.scholarships.services.implementation;

import com.api.scholarships.services.interfaces.CloudService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryService implements CloudService {
    private final Cloudinary cloudinary;
    private final Dotenv dotenv=Dotenv.load();
    private CloudinaryService(){
        Map<String,String> valuesCloudinary=new HashMap<>();
        valuesCloudinary.put("cloud_name",dotenv.get("CLOUD_NAME"));
        valuesCloudinary.put("api_key",dotenv.get("API_KEY"));
        valuesCloudinary.put("api_sercret",dotenv.get("API_SECRET"));
        cloudinary = new Cloudinary(valuesCloudinary);
    }

    @Override
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result= cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
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


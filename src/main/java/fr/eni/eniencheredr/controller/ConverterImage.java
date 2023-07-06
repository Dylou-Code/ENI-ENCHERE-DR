package fr.eni.eniencheredr.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ConverterImage implements Converter<MultipartFile, String> {

/*    @Override
    public String convert(MultipartFile source) {
        return null;
    }*/
    @Override
    public String convert(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "./src/main/resources/static/images";

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath);

            return "/images/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

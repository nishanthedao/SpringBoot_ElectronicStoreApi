package com.nish.store.services.impl;

import com.nish.store.exceptions.BadApiRequest;
import com.nish.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename : {}", originalFilename);
        UUID filename = UUID.randomUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename+extension;
        String fullPathWithFileName = path+ File.separator+fileNameWithExtension;
        if(extension.equalsIgnoreCase(".png") ||
                extension.equalsIgnoreCase(".jpg") ||
                extension.equalsIgnoreCase(".jpeg")){
            //Save file
            File folder = new File(path);
            if(!folder.exists()){
                // Create folder
                folder.mkdirs();
            }
            // upload file
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }
        else{
            throw new BadApiRequest("File with this "+extension+" not allowed");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path+File.separator+name;
        InputStream stream = new FileInputStream(fullPath);
        return stream;
    }
}

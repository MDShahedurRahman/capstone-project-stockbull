package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.validator.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    // The path to the upload directory where files will be saved
    // Change this path as per your computer
    public String uploadDir = "/Users/MD/Desktop/Capstone_Project/stockbull/src/main/resources/static/images";

    // Method to upload a file
    public void uploadFile(MultipartFile file) {
        try {
            // Create the destination path where the file will be copied
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            // Copy the file's InputStream to the destination path
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            // Get the original file name and its length for logging purposes
            String fileName = file.getOriginalFilename();
            int filelength = file.getBytes().length;

            // Print a success message with the uploaded file details
            System.out.println("File uploaded successfully, " + "file name is :: " + fileName +
                    " and length is :: " + filelength);
        } catch (IOException e) {
            e.printStackTrace();
            // If an exception occurs during file upload, throw a custom FileStorageException
            throw new FileStorageException("File Not Found");
        }
    }
}

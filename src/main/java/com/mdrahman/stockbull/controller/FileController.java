package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    // Handler method for uploading a single file
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message1", "Please select a file to upload.");
            return "redirect:/profile";
        } else {
            // Call the fileService to upload the file
            fileService.uploadFile(file);
            redirectAttributes.addFlashAttribute("message2", "You have successfully uploaded. Allow 24 hours to validate!");
            return "redirect:/profile";
        }
    }

    // Handler method for uploading multiple files
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files,
                              RedirectAttributes redirectAttributes) {
        // Loop through each file in the array and call the fileService to upload it
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded all files!");
        return "redirect:/";
    }
}

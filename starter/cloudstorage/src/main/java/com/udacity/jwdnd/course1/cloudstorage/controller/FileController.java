package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile( @RequestParam("fileUpload") MultipartFile fileUpload,
                              Authentication authentication, Model model) {

        if (fileUpload.isEmpty()) {
            model.addAttribute("isSuccess", false);
            return "redirect:/result?isSuccess=" + false + "&errorType= empty";
        }

        String username = (String) authentication.getPrincipal();
        int userId = fileService.getUserID(username);
        List<File> userFiles = fileService.getFiles(userId);
        for (File file: userFiles) {
            if (file.getFileName().equals(fileUpload.getOriginalFilename())) {
                model.addAttribute("isSuccess", false);
                return "redirect:/result?isSuccess=" + false + "&errorType=duplicate";
            }
        }

        try {
            this.fileService.addFile(fileUpload, username);
            model.addAttribute("fileList", fileService.getFiles(fileService.getUserID(username)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/result?isSuccess=" + true;
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam(required = false, name = "fileid") Integer fileid) {
        Boolean isSuccess = this.fileService.deleteFile(fileid);
        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("download")
    public ResponseEntity<InputStreamResource> viewFile(@RequestParam(required = false, name = "fileid") Integer fileid) {
        File file = fileService.getFileById(fileid);
        String fileName = file.getFileName();
        String contentType = file.getContentType();
        byte[] fileData = file.getFileData();

        InputStream inputStream = new ByteArrayInputStream(fileData);

        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
package com.ai_agent.file_processor.controllers;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.services.fileServices.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam ("file") MultipartFile file,
            @RequestHeader ("Authorization") String token
            ) throws UnsupportedFileTypeException {

        // token validation code will be here so the file service part will only
        // handle the file processing part

        String summary = fileService.getSummaryOfTheFile(file, 10L);


        return null;
    }

}

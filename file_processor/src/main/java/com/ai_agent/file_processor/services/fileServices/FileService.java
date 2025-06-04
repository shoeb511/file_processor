package com.ai_agent.file_processor.services.fileServices;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String getSummaryOfTheFile(MultipartFile file, Long userId) throws UnsupportedFileTypeException;
}

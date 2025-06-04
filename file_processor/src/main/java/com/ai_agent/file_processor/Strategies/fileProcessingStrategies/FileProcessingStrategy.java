package com.ai_agent.file_processor.Strategies.fileProcessingStrategies;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessingStrategy {

    public String processFile(MultipartFile file);
}

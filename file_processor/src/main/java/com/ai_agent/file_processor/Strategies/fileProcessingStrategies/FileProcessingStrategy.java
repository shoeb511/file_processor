package com.ai_agent.file_processor.Strategies.fileProcessingStrategies;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileProcessingStrategy {

    public String processFile(List<String> chunks);

}
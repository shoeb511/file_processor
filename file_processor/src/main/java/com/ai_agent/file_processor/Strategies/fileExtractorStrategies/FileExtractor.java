package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.springframework.web.multipart.MultipartFile;

public interface FileExtractor {

    public String extractFile(MultipartFile file);
}

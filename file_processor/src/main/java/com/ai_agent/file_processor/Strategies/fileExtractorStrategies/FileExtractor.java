package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileExtractor {

    public List<String> extractFile(MultipartFile file);
}

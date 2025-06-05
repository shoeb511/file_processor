package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DocxFileExtractor implements FileExtractor {

    @Override
    public List<String> extractFile(MultipartFile file) {
        return "";
    }
}

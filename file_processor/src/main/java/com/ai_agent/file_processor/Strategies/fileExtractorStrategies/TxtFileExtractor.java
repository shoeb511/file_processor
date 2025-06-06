package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TxtFileExtractor implements FileExtractor {

    @Override
    public List<String> extractFile(MultipartFile file) throws IOException {

        System.out.println("abi control file extractor me he : " + file.getOriginalFilename());

        List<String> chunks = new ArrayList<>();
        StringBuilder currentChunk = new StringBuilder();
        int chunkSize = 60000;

        String fullText = new String(file.getBytes(), StandardCharsets.UTF_8);

        for (int i = 0; i < fullText.length(); i += chunkSize) {
            int end = Math.min(i + chunkSize, fullText.length());
            chunks.add(fullText.substring(i, end));
        }

        return chunks;
    }
}

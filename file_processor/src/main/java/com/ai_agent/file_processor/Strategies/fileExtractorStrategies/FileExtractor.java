package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public interface FileExtractor {

    public List<String> extractFile(MultipartFile file) throws IOException, TikaException, SAXException;
}

package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;


import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DocxFileExtractor implements FileExtractor {

    @Override
    public List<String> extractFile(MultipartFile file) throws IOException, TikaException, SAXException {

//
        return null;
    }
}




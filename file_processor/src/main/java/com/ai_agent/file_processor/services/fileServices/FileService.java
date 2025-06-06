package com.ai_agent.file_processor.services.fileServices;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface FileService {

    public String getSummaryOfTheFile(MultipartFile file, Long userId) throws UnsupportedFileTypeException, IOException, TikaException, SAXException;
}

package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.models.fileModel.FileType;

public class FileExtractorFactory {

    public FileExtractor getFileExtractor(FileType fileType) throws UnsupportedFileTypeException {
        switch (fileType) {
            case PDF -> new PdfFileExtractor();
            case DOCX -> new DocxFileExtractor();
            //default -> throw new UnsupportedFileTypeException("this file format not supported ...");
        }
        throw new UnsupportedFileTypeException("Unsupported file type: " + fileType);
    }
}

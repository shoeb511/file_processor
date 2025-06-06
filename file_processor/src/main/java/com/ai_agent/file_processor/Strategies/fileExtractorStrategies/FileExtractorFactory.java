package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.models.fileModel.FileType;

public class FileExtractorFactory {

    public FileExtractor getFileExtractor(FileType fileType) throws UnsupportedFileTypeException {
        if (fileType.equals(FileType.TXT)) {
            return new TxtFileExtractor();
        }

        else if (fileType.equals(FileType.PDF)) {
            return new DocxFileExtractor();
        }

        else if (fileType.equals(FileType.DOCX)){
            return new DocxFileExtractor();
        }

        else {
            throw new UnsupportedFileTypeException("Unsupported file type: " + fileType);
        }
    }
}

package com.ai_agent.file_processor.Strategies.fileProcessingStrategies;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.models.fileModel.FileType;

public class FIleProcesingStrategyFactory {

    public FileProcessingStrategy getFileProcessingStrategy(FileType fileType) throws UnsupportedFileTypeException {

        if(fileType.equals(FileType.PDF)){
            return new PdfFileProcessingStrategy();
        }
        else if(fileType.equals(FileType.DOCX)){
            return new DocxFileProcessingStrategy();
        }
        else {
            throw new UnsupportedFileTypeException("the file format is not valid...");
        }
    }
}

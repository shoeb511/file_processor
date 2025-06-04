package com.ai_agent.file_processor.services.fileServices;

import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.models.fileModel.File;
import com.ai_agent.file_processor.models.fileModel.FileType;
import com.ai_agent.file_processor.repos.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public String getSummaryOfTheFile(MultipartFile file, Long userId) throws UnsupportedFileTypeException {

        File newFile = new File();

        String fileName = file.getOriginalFilename();
        FileType fileType ;
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        switch (extension) {
            case "txt": fileType = FileType.TXT;
            break;
            case "doc": fileType = FileType.DOCX;
            break;
            case "docx": fileType = FileType.DOCX;
            break;
            case "pdf": fileType = FileType.PDF;
            break;
           // default: throw new UnsupportedFileTypeException("invalid file extension ...");
        }

        if(!fileType.equals(FileType.DOCX) || !fileType.equals(FileType.PDF)
            || !fileType.equals(FileType.JPEG) || !fileType.equals(FileType.TXT)){
            throw  new UnsupportedFileTypeException("invalid file extension....");
        }

        Long fileSize = file.getSize();

        newFile.setFilename(fileName);
        newFile.setFileType(fileType);
        newFile.setSize(fileSize);
        newFile.setUserId(userId);

        fileRepository.save(newFile);

    }
}

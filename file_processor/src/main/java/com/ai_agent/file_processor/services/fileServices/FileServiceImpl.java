package com.ai_agent.file_processor.services.fileServices;

import com.ai_agent.file_processor.Strategies.fileExtractorStrategies.FileExtractor;
import com.ai_agent.file_processor.Strategies.fileExtractorStrategies.FileExtractorFactory;
import com.ai_agent.file_processor.exceptions.UnsupportedFileTypeException;
import com.ai_agent.file_processor.models.fileModel.File;
import com.ai_agent.file_processor.models.fileModel.FileType;
import com.ai_agent.file_processor.repos.FileRepository;
import com.ai_agent.file_processor.services.aiModelService.GeminiModelService;
import com.ai_agent.file_processor.services.aiModelService.LlmModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

//    @Autowired
//    FileRepository fileRepository;


     private LlmModelService llmModelService;

     // injecting the LLMModuelservice in Fileserviceimpl using constructor injection
     public FileServiceImpl(LlmModelService llmModelService) {
         this.llmModelService = llmModelService;
     }


    @Override
    public String getSummaryOfTheFile(MultipartFile file, Long userId) throws UnsupportedFileTypeException, IOException, TikaException, SAXException {

        File newFile = new File();

        // logging
        System.out.println("abi hum fileservice impl me he ");

        String fileName = file.getOriginalFilename();

        // logging
        System.out.println("file ka nam : " + fileName);
        System.out.println(("LLm model service ka object : " + llmModelService.toString()));

        FileType fileType = null ;
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        // logging
        System.out.println("file ka extension : " + extension);

        switch (extension) {
            case "txt": fileType = FileType.TXT;
            break;
            case "doc": fileType = FileType.DOCX;
            break;
            case "docx": fileType = FileType.DOCX;
            break;
            case "pdf": fileType = FileType.PDF;
            break;
        }

        if(!fileType.equals(FileType.DOCX) && !fileType.equals(FileType.PDF)
            && !fileType.equals(FileType.JPEG) && !fileType.equals(FileType.TXT)){
            throw  new UnsupportedFileTypeException("invalid file extension....");
        }

        Long fileSize = file.getSize();

        newFile.setFilename(fileName);
        newFile.setFileType(fileType);
        newFile.setSize(fileSize);
        newFile.setUserId(userId);

        System.out.println(newFile.toString());

        // here we will store the uploaded file in the db probably temporarily
        //fileRepository.save(newFile);

        // get the object of file extractor and get the chunks of the extracted text from the file
        FileExtractorFactory fileExtractorFactory = new FileExtractorFactory();

        FileExtractor fileExtractor = fileExtractorFactory.getFileExtractor(fileType);
        System.out.println("file extractor object " + fileExtractor.toString());

        // the file extrcter class will return the list of chunks , means it will return
        // the parts of the whole string / text extracted from the file
        List<String> chunks = fileExtractor.extractFile(file);

        // this will store the overall summary comming from the api call
        StringBuilder fullSummary = new StringBuilder();

        for(String chunk : chunks){
            System.out.println("chunk size is : -> " + chunk.length());
            System.out.println("llm model service ka object : " + llmModelService.toString());

            fullSummary.append(llmModelService.getSummary(chunk));
        }

        return fullSummary.toString();
    }
}

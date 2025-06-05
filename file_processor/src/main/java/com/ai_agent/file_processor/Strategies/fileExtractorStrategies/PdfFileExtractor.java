package com.ai_agent.file_processor.Strategies.fileExtractorStrategies;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfFileExtractor implements FileExtractor {

    @Override
    public List<String> extractFile(MultipartFile file) {

        List<String> chunks = new ArrayList<>();
        int chunkSize = 3000;

        try {
            InputStream inputStream = file.getInputStream();
            PDDocument pdDocument = Loader.loadPDF((RandomAccessRead) inputStream);

            PDFTextStripper stripper = new PDFTextStripper();
            String fullText = stripper.getText(pdDocument);
            for(int i = 0; i < fullText.length(); i += chunkSize) {
                int end = Math.min(i + chunkSize, fullText.length());
                chunks.add(fullText.substring(i, end));
            }

            return chunks;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

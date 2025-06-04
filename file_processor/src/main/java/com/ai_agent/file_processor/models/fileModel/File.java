package com.ai_agent.file_processor.models.fileModel;

import com.ai_agent.file_processor.models.BaseModel;
import jakarta.persistence.Entity;

@Entity
public class File extends BaseModel {

    private String filename;
    private FileType fileType;
    private Long size;
    private Long userId;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

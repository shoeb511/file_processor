package com.ai_agent.file_processor.repos;

import com.ai_agent.file_processor.models.fileModel.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}

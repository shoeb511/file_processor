package com.ai_agent.file_processor.services.aiModelService;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface LlmModelService {

    public String getSummary(String chunk) throws JsonProcessingException;
}

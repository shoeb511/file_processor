package com.ai_agent.file_processor.services.aiModelService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiModelService implements LlmModelService{

    //@Autowired
    private RestTemplate restTemplate;

    public GeminiModelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";
    String apiKey = "cannot expose the api key in public repository" ;

    @Override
    public String getSummary(String chunk) throws JsonProcessingException {

        System.out.println("abi control gemini servcie me he ");

        HttpHeaders headers = new HttpHeaders();

        String completeUrl = url + apiKey;

        headers.add("content-type", "application/json");
        String requestJson = geminiRequestBuilder(chunk);

        HttpEntity request = new HttpEntity(requestJson, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(completeUrl, HttpMethod.POST, request, String.class);
            System.out.println("ye json request he gemini api ke liey : " + requestJson.toString());
            return response.getBody();
        }
        catch (Exception e) {
            return "gemini api ka error , " + e.getMessage();
        }


    }

    private String geminiRequestBuilder(String content) throws JsonProcessingException {

        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", "please provide a short summary of the given content ," +content);

        Map<String, Object> partWrapper = new HashMap<>();
        partWrapper.put("parts", List.of(textPart));

        Map<String, Object> requestJson = new HashMap<>();
        requestJson.put("contents", partWrapper);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(requestJson);
    }
}

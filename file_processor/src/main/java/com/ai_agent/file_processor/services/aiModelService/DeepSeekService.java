//package com.ai_agent.file_processor.services.aiModelService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import org.springframework.http.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DeepSeekService implements LlmModelService{
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Override
//    public String getSummary(String chunk) {
//
//        String apiKey = "sk-95207501e9424f21a77329cb0908847d";
//        String url = "https://api.deepseek.com/chat/completions";
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", "deepseek-chat");
//
//        List<Map<String, String>> messages = new ArrayList<>();
//        messages.add(Map.of("role", "system", "content", "You are a helpful assistant, please summarize the user's content; "));
//        messages.add(Map.of("role", "user", "content", chunk));
//
//        requestBody.put("messages", messages);
//        requestBody.put("stream", false);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
//
//        try{
//            ResponseEntity<Map> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    request,
//                    Map.class
//            );
//
//            if(response.getStatusCode() == HttpStatus.OK){
//                Map choices = ((List<Map>) response.getBody().get("choices")).get(0);
//                Map message = (Map) choices.get("messages");
//                return message.get("content").toString();
//            }
//            else{
//                return "error : " + response.getStatusCode();
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return "Exception occured : " + e.getMessage();
//        }
//
//    }
//}

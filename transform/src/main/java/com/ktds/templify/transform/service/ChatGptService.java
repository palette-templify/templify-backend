package com.ktds.templify.transform.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    @Value("${chatgpt.api.url}")
    private String OPENAI_URL;

    @Value("${chatgpt.api.key}")
    private String API_KEY;

    @Value("${chatgpt.api.model}")
    private String MODEL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // 공통 헤더 생성 메서드
    private HttpHeaders createHeaders(MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        headers.setContentType(contentType);
        return headers;
    }

    // 📌 OpenAI 파일 업로드 (기존 uploadFile 메서드 대체)
    public String uploadFile(MultipartFile file) {
        HttpHeaders headers = createHeaders(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("purpose", "assistants");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            OPENAI_URL + "/files",
            requestEntity,
            String.class
        );

        return extractFileId(response.getBody());
    }

    // 📌 GPT-4에게 파일을 기반으로 변환 요청
    public String transformContent(String originalText, String templateFileId) {
        HttpHeaders headers = createHeaders(MediaType.APPLICATION_JSON);

        // 요청 본문 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", MODEL);
        requestBody.put("temperature", 0.7);

        List<Map<String, Object>> messages = new ArrayList<>();

        // 시스템 메시지
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are an AI that reformats text according to a given template. A template file id will be given.");
        messages.add(systemMessage);

        // 사용자 메시지
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", originalText);
        userMessage.put("file_ids", List.of(templateFileId));
        messages.add(userMessage);

        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
            OPENAI_URL + "/chat/completions",
            requestEntity,
            String.class
        );

        return extractResponseContent(response.getBody());
    }

    // 파일 ID 추출 헬퍼 메서드
    private String extractFileId(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            return rootNode.has("id") ? rootNode.get("id").asText() : null;
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 응답 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 응답 컨텐츠 추출 헬퍼 메서드
    private String extractResponseContent(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode choices = rootNode.get("choices");

            if (choices != null && choices.isArray() && choices.size() > 0) {
                return choices.get(0).get("message").get("content").asText();
            }
            return "변환 실패!";
        } catch (Exception e) {
            throw new RuntimeException("응답 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }

}

package com.ktds.templify.transform.service;

import com.ktds.templify.transform.client.HistoryClient;
import com.ktds.templify.transform.dto.ChatGptExtractedResponseDto;
import com.ktds.templify.transform.dto.HistoryRequest;
import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.entity.Transform;
import com.ktds.templify.transform.repository.TransformRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransformService {

    private final ChatGptService chatGptService;
    private final TransformRequestRepository requestRepository;
    private final HistoryClient historyClient;

    public TransformResponse transform(TransformRequest request) {
        ChatGptExtractedResponseDto chatGptExtractedResponseDto = chatGptService.transformContent(
            request.getArticleContent(),
            request.getTemplateContent()
        );

        // transform 저장
        Transform transform = Transform.builder()
            .articleId(request.getArticleId())
            .userId(request.getUserId())
            .status("SUCCESS")
            .build();
        transform = requestRepository.save(transform);

        // history 저장
        HistoryRequest historyRequest = HistoryRequest.builder()
            .requestId(transform.getId())
            .userId(transform.getUserId())
            .templateName(request.getTemplateName())
            .articleTitle(request.getArticleTitle())
            .originalText(request.getArticleContent())
            .transformedText(chatGptExtractedResponseDto.getContent())
            .modelName(request.getModelName())
            .tokenCount(chatGptExtractedResponseDto.getTotalTokens())
            .createdAt(transform.getCreatedAt())
            .articleId(request.getArticleId())
            .build();
        historyClient.createHistory(historyRequest);

        return TransformResponse.builder()
            .transformedContent(chatGptExtractedResponseDto.getContent())
            .build();
    }

}

package com.ktds.templify.transform.service;

import com.ktds.templify.common.service.KafkaProducerService;
import com.ktds.templify.transform.dto.ChatGptExtractedResponseDto;
import com.ktds.templify.transform.dto.HistoryRequest;
import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.entity.Transform;
import com.ktds.templify.transform.repository.TransformRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaTransformConsumer {

    private final ChatGptService chatGptService;
    private final TransformRequestRepository transformRequestRepository;
    private final KafkaProducerService kafkaProducerService;


    @KafkaListener(topics = "write-topic", groupId = "transform-group")
    public void processTransformRequest(TransformRequest request) {
        Transform transform = initiateTransform(request);

        ChatGptExtractedResponseDto chatGptExtractedResponseDto = chatGptService.transformContent(
            request.getArticleContent(),
            request.getTemplateContent()
        );

        if (chatGptExtractedResponseDto.getTotalTokens() != 0) {
            updateTransformStatus(transform, "SUCCESS");
        } else {
            updateTransformStatus(transform, "FAILED");
        }


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

        kafkaProducerService.sendMessage("transform-topic", historyRequest);
    }

    public Transform initiateTransform(TransformRequest request) {
        Transform transform = Transform.builder()
            .articleId(request.getArticleId())
            .userId(request.getUserId())
            .status("TRANSFORMING")
            .build();
        transformRequestRepository.save(transform);

        return transform;
    }

    public void updateTransformStatus(Transform transform, String status) {
        transform.updateStatus(status);
        transformRequestRepository.save(transform);
    }
}

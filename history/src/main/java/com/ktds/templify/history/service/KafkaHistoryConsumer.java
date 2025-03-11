package com.ktds.templify.history.service;

import com.ktds.templify.history.dto.HistoryRequest;
import com.ktds.templify.history.entity.History;
import com.ktds.templify.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaHistoryConsumer {

    private final HistoryRepository historyRepository;

    @Transactional
    @KafkaListener(topics = "transform-topic", groupId = "history-group")
    public void processHistoryRequest(HistoryRequest request) {
        History history = historyRepository.findByArticleId(request.articleId())
            .orElseGet(() -> {
                History newHistory = History.builder()
                    .requestId(request.requestId())
                    .userId(request.userId())
                    .templateName(request.templateName())
                    .articleTitle(request.articleTitle())
                    .originalText(request.originalText())
                    .transformedText(request.transformedText())
                    .modelName(request.modelName())
                    .tokenCount(request.tokenCount())
                    .createdAt(request.createdAt())
                    .articleId(request.articleId())
                    .build();
                return historyRepository.save(newHistory);
            });

        if (request.tokenCount() != -1) {
            history.updateTransformedData(request.transformedText(), request.tokenCount());
            historyRepository.save(history);
        }
    }
}

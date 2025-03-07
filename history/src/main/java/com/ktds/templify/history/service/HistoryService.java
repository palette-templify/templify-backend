package com.ktds.templify.history.service;

import com.ktds.templify.history.dto.HistoryRequest;
import com.ktds.templify.history.dto.HistoryResponse;
import com.ktds.templify.history.dto.HistoryDetailResponse;
import com.ktds.templify.history.entity.History;
import com.ktds.templify.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    
    private final HistoryRepository historyRepository;

    @Transactional
    public void createHistory(HistoryRequest request) {
        History history = History.builder()
            .requestId(request.requestId())
            .userId(request.userId())
            .templateName(request.templateName())
            .articleTitle(request.articleTitle())
            .originalText(request.originalText())
            .transformedText(request.transformedText())
            .modelName(request.modelName())
            .tokenCount(request.tokenCount())
            .createdAt(request.createdAt())
            .build();

        historyRepository.save(history);
    }
    
    @Transactional(readOnly = true)
    public List<HistoryResponse> getHistories(Long userId) {
        return historyRepository.findByUserId(userId).stream()
            .map(history -> HistoryResponse.builder()
                .id(history.getId())
                .requestId(history.getRequestId())
                .templateName(history.getTemplateName())
                .articleTitle(history.getArticleTitle())
                .originalText(history.getOriginalText())
                .transformedText(history.getTransformedText())
                .createdAt(history.getCreatedAt())
                .build()
            )
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public HistoryDetailResponse getHistory(Long id, Long userId) {
        var history = historyRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new IllegalArgumentException("History not found"));
            
        return new HistoryDetailResponse(
            history.getId(),
            history.getRequestId(),
            history.getOriginalText(),
            history.getTransformedText(),
            history.getTemplateName(),
            history.getModelName(),
            history.getTokenCount(),
            history.getCreatedAt()
        );
    }
}

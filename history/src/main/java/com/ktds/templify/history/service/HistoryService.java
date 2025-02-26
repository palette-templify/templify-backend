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
import org.springframework.web.bind.annotation.RequestBody;

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
            .originalText(request.originalText())
            .transformedText(request.transformedText())
            .modelName(request.modelName())
            .tokenCount(request.tokenCount())
            .processingTime(request.processingTime())
            .createdAt(request.createdAt())
            .build();

        historyRepository.save(history);
    }
    
    @Transactional(readOnly = true)
    public List<HistoryResponse> getHistories(String userId) {
        return historyRepository.findByUserId(userId).stream()
            .map(history -> new HistoryResponse(
                history.getId(),
                history.getRequestId(),
                history.getOriginalText(),
                history.getTransformedText(),
                history.getCreatedBy(),
                history.getCreatedAt()
            ))
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public HistoryDetailResponse getHistory(Long id, String userId) {
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
            history.getProcessingTime(),
            history.getCreatedAt()
        );
    }
}

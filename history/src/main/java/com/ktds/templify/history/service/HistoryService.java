package com.ktds.templify.history.service;

import com.ktds.templify.history.dto.HistoryResponse;
import com.ktds.templify.history.dto.HistoryDetailResponse;
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
    
    @Transactional(readOnly = true)
    public List<HistoryResponse> getHistories(String userId) {
        return historyRepository.findByUserId(userId).stream()
            .map(history -> new HistoryResponse(
                history.getId(),
                history.getRequestId(),
                history.getOriginalText(),
                history.getTransformedAt(),
                "COMPLETED"
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
            history.getTransformedAt()
        );
    }
}

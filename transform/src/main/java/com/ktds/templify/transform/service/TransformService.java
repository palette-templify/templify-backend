package com.ktds.templify.transform.service;

import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.dto.TransformStatus;
import com.ktds.templify.transform.entity.TransformRequestEntity;
import com.ktds.templify.transform.repository.TransformRequestRepository;
import com.ktds.templify.transform.repository.TransformResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransformService {
    
    private final TransformRequestRepository requestRepository;
    private final TransformResultRepository resultRepository;

    @Transactional
    public TransformResponse createTransformRequest(TransformRequest request, String userId) {
        String requestId = UUID.randomUUID().toString();

        TransformRequestEntity entity = TransformRequestEntity.builder()
            .requestId(requestId)
            .articleId(request.getArticleId())
            .userId(userId)
            .status("PENDING")
            .build();

        requestRepository.save(entity);  // 실제 저장 로직 추가

        return new TransformResponse(requestId, "PENDING");
    }

    public TransformStatus getStatus(String requestId) {
        TransformRequestEntity entity = requestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("Transform request not found"));

        return new TransformStatus(requestId, entity.getStatus(), calculateProgress(entity));
    }

    private Integer calculateProgress(TransformRequestEntity entity) {
        // 진행률 계산 로직
        return switch (entity.getStatus()) {
            case "PENDING" -> 0;
            case "IN_PROGRESS" -> 50;
            case "COMPLETED" -> 100;
            default -> 0;
        };
    }
}

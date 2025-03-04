package com.ktds.templify.transform.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record HistoryRequest(
    Long requestId,
    Long userId,
    String templateName,
    String originalText,
    String transformedText,
    String modelName,
    Integer tokenCount,
    LocalDateTime createdAt
) {}

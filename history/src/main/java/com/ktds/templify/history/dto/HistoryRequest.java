package com.ktds.templify.history.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record HistoryRequest(
    Long requestId,
    Long userId,
    Long articleId,
    String templateName,
    String articleTitle,
    String originalText,
    String transformedText,
    String modelName,
    Integer tokenCount,
    LocalDateTime createdAt
) {}
package com.ktds.templify.history.dto;

import java.time.LocalDateTime;

public record HistoryRequest (
    String requestId,
    Long userId,
    String templateName,
    String originalText,
    String transformedText,
    String modelName,
    Integer tokenCount,
    Integer processingTime,
    LocalDateTime createdAt
) {}

package com.ktds.templify.history.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String requestId;
    private String originalText;
    private String transformedText;
    private String status;
    private LocalDateTime createdAt;
}

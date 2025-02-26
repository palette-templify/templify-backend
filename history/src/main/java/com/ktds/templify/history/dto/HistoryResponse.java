package com.ktds.templify.history.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String requestId;
    private String originalText;
    private String transformedText;
    private LocalDateTime createdAt;
}

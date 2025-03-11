package com.ktds.templify.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class HistoryDetailResponse {
    private Long id;
    private Long requestId;
    private String originalText;
    private String transformedText;
    private String templateName;
    private String modelName;
    private Integer tokenCount;
    private LocalDateTime transformedAt;
}

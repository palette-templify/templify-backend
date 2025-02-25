package com.ktds.templify.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String requestId;
    private String originalText;
    private LocalDateTime transformedAt;
    private String status;
}

package com.ktds.templify.transform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGptExtractedResponseDto {
    private String content;
    private int totalTokens;
}
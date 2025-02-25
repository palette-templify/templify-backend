package com.ktds.templify.history.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExportRequest {
    private String format; // PDF, WORD, TEXT
}

package com.ktds.templify.transform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TransformResponse {
    private String transformedContent;
    private String status;
}

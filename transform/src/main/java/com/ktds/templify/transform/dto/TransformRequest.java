package com.ktds.templify.transform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformRequest {
    private Long userId;
    private Long articleId;
    private String articleContent;
    private String templateContent;
    private String templateName;
    private Long modelId;
    private String modelName;
}



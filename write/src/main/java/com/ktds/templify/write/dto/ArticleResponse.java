package com.ktds.templify.write.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private Long id;
    private String title;
    private String originalContent;
    private String transformedContent;
    private String templateName;
    private String modelName;
}

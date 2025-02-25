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
    private Long articleId;
    private Long modelId;
    private Long templateId;
}



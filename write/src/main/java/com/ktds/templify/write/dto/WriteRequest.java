package com.ktds.templify.write.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class WriteRequest {
    private String title;
    private String content;
    private Long templateId;
    private Long modelId;
}

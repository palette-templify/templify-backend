package com.ktds.templify.write.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TemplateResponse {
    private Long id;
    private String name;
    private String description;
}

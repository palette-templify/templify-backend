package com.ktds.templify.transform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransformStatus {
    private String requestId;
    private String status;
    private Integer progress;
}

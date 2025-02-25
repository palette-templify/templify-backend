package com.ktds.templify.transform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransformResponse {
    private String requestId;
    private String status;
}

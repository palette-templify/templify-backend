package com.ktds.templify.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * API 응답을 위한 공통 응답 객체
 */
@Getter
public class ApiResponse<T> {
    private final Integer status;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    @JsonCreator // Jackson이 이 생성자를 사용할 수 있도록 지정
    public ApiResponse(
        @JsonProperty("status") Integer status,
        @JsonProperty("message") String message,
        @JsonProperty("data") T data,
        @JsonProperty("timestamp") LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(Integer status, String message) {
        return new ApiResponse<>(status, message, null, LocalDateTime.now());
    }
}

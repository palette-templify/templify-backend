package com.ktds.templify.common.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    @DisplayName("성공 응답을 생성할 수 있다")
    void createSuccessResponse() {
        // given
        String data = "test data";

        // when
        ApiResponse<String> response = ApiResponse.success(data);

        // then
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    @DisplayName("에러 응답을 생성할 수 있다")
    void createErrorResponse() {
        // given
        int errorStatus = 400;
        String errorMessage = "Bad Request";

        // when
        ApiResponse<Object> response = ApiResponse.error(errorStatus, errorMessage);

        // then
        assertThat(response.getStatus()).isEqualTo(errorStatus);
        assertThat(response.getMessage()).isEqualTo(errorMessage);
        assertThat(response.getData()).isNull();
        assertThat(response.getTimestamp()).isNotNull();
    }
}

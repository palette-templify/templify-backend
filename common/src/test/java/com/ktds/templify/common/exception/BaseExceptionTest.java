package com.ktds.templify.common.dto;

import com.ktds.templify.common.exception.BaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BaseExceptionTest {

    @Test
    @DisplayName("BaseException이 메시지와 코드를 가진다")
    void createBaseException() {
        // given
        String message = "test message";
        String code = "TEST_CODE";

        // when
        BaseException exception = new BaseException(message, code);

        // then
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCode()).isEqualTo(code);
    }
}

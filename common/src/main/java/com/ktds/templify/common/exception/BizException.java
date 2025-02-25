package com.ktds.templify.common.exception;

/**
 * 비즈니스 로직 처리 중 발생하는 예외를 처리하는 클래스
 */
public class BizException extends BaseException {
    public BizException(String message) {
        super(message, "BIZ_ERROR");
    }
}

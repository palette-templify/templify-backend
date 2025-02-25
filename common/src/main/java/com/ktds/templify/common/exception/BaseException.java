package com.ktds.templify.common.exception;

import lombok.Getter;

/**
 * 모든 예외의 기본이 되는 기초 예외 클래스
 */
@Getter
public class BaseException extends RuntimeException {
    private final String code;
    
    public BaseException(String message, String code) {
        super(message);
        this.code = code;
    }
}

package com.ktds.templify.common.exception;

/**
 * 인프라스트럭처 관련 예외를 처리하는 클래스
 */
public class InfraException extends BaseException {
    public InfraException(String message) {
        super(message, "INFRA_ERROR");
    }
}

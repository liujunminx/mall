package com.cloud.user.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

    private final HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException(String message) {
        super(message);
    }

    public HttpStatus getCode() {
        return code;
    }
}

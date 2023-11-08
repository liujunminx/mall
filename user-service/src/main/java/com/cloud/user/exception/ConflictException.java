package com.cloud.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ConflictException extends RuntimeException {

    private final HttpStatus code = HttpStatus.CONFLICT;

    public ConflictException(String message) {
        super(message);
    }

    public HttpStatus getCode() {
        return code;
    }
}

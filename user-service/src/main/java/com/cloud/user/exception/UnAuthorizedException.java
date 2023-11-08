package com.cloud.user.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RuntimeException{

    private final HttpStatus code = HttpStatus.UNAUTHORIZED;

    public UnAuthorizedException(String message) {
        super(message);
    }

    public HttpStatus getCode() {
        return code;
    }
}

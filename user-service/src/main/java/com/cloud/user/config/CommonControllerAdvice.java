package com.cloud.user.config;

import com.cloud.user.exception.ConflictException;
import com.cloud.user.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<String> handleConflict(Exception e) {
        ConflictException ex = (ConflictException) e;
        ResponseEntity.ok();
        return new ResponseEntity<>(ex.getMessage(), ex.getCode());
    }

    @ResponseBody
    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<String> handleUnAuthorized(Exception e) {
        UnAuthorizedException ex = (UnAuthorizedException) e;
        return new ResponseEntity<>(ex.getMessage(), ex.getCode());
    }
}

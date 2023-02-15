package com.example.mockspringbootapi.config;

import com.example.mockspringbootapi.exception.UnauthenticatedException;
import com.example.mockspringbootapi.web.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthenticatedException(UnauthenticatedException ex) {
        logger.info(ex.getMessage(), ex);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "", ex.getMessage(), ex.getDetail(), "認証されていません。ログインしてください。");
    }
}

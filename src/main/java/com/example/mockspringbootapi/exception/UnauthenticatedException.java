package com.example.mockspringbootapi.exception;

import lombok.Getter;

@Getter
public class UnauthenticatedException extends Exception {
    private String detail;

    public UnauthenticatedException(String message) {
        super(message);
        this.detail = Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
    }
}

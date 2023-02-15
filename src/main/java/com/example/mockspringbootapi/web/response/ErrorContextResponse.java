package com.example.mockspringbootapi.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorContextResponse {
    private Integer status;
    private String code;
    private String summary;
    private String detail;
    private String message;
}

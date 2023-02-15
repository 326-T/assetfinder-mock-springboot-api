package com.example.mockspringbootapi.web.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private ErrorContextResponse error;

    @JsonCreator
    public ErrorResponse(Integer status, String code, String summary, String detail, String message) {
        this.error = new ErrorContextResponse(status, code, summary, detail, message);
    }
}

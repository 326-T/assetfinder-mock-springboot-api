package com.example.mockspringbootapi.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.stream.Stream;

public class AuthorizationUtil {

    public static String getHeader(HttpServletRequest request) {
        Optional<String> header = Optional.ofNullable(request.getHeader("Authorization"));
        return Stream.of(header.orElse("").split("Bearer ")).skip(1).reduce("", (result, str) -> result + str);
    }
}

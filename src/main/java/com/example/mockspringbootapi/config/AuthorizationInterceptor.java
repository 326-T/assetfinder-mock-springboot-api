package com.example.mockspringbootapi.config;

import com.example.mockspringbootapi.annotation.NonAuth;
import com.example.mockspringbootapi.exception.UnauthenticatedException;
import com.example.mockspringbootapi.util.AuthorizationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private String AUTH_TOKEN;
    public AuthorizationInterceptor(@Value("${constants.auth-token}")String token) {
        AUTH_TOKEN = token;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object instance) throws Exception {
        if (!(instance instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) instance;
        Method method = handlerMethod.getMethod();
        NonAuth nonAuth = AnnotationUtils.findAnnotation(method, NonAuth.class);
        if (Objects.nonNull(nonAuth))
            return true;
        if (Objects.equals(AuthorizationUtil.getHeader(request), AUTH_TOKEN))
            return true;
        throw new UnauthenticatedException("認証されていません。");
    }
}

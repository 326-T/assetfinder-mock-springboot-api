package com.example.mockspringbootapi.it;

import com.example.mockspringbootapi.Application;
import com.example.mockspringbootapi.web.response.ErrorResponse;
import com.example.mockspringbootapi.web.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthAPITest {

    @Autowired
    private final TestRestTemplate template = new TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void setup() {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Nested
    public class currentUser {
        @Nested
        public class 正常系 {
            @Test
            public void headerのtokenが有効だとtokenに一致するユーザを返す() {
                headers.add("Authorization", "Bearer test-token");
                ResponseEntity<UserResponse> userResponseResponseEntity = template.exchange("/api/v1/auth/current-user", HttpMethod.GET, new HttpEntity<>(headers), UserResponse.class);
                assertThat(userResponseResponseEntity.getStatusCode().value()).isEqualTo(200);
                assertThat(userResponseResponseEntity.getBody().getId()).isEqualTo(1);
                assertThat(userResponseResponseEntity.getBody().getName()).isEqualTo("privilege");
                assertThat(userResponseResponseEntity.getBody().getEmail()).isEqualTo("xxx@gmail.com");
                assertThat(userResponseResponseEntity.getBody().getToken()).isEqualTo("test-token");
            }
        }
        @Nested
        public class 異常系 {
            @Test
            public void headerのtokenが無効だと401レスポンスする() {
                headers.add("Authorization", "Bearer invalid-token");
                ResponseEntity<ErrorResponse> userResponseResponseEntity = template.exchange("/api/v1/auth/current-user", HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);
                assertThat(userResponseResponseEntity.getStatusCode().value()).isEqualTo(401);
                assertThat(userResponseResponseEntity.getBody().getError().getStatus()).isEqualTo(401);
                assertThat(userResponseResponseEntity.getBody().getError().getCode()).isEqualTo("");
                assertThat(userResponseResponseEntity.getBody().getError().getSummary()).isEqualTo("認証されていません。");
                assertThat(userResponseResponseEntity.getBody().getError().getDetail()).isEqualTo("com.example.mockspringbootapi.config.AuthorizationInterceptor.preHandle()");
                assertThat(userResponseResponseEntity.getBody().getError().getMessage()).isEqualTo("認証されていません。ログインしてください。");
            }
        }
    }
}

package com.example.mockspringbootapi.it;

import com.example.mockspringbootapi.Application;
import com.example.mockspringbootapi.web.response.D42RackInfoResponse;
import com.example.mockspringbootapi.web.response.ErrorResponse;
import com.example.mockspringbootapi.web.response.InventoryDateResponse;
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
public class D42RackAPITest {

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
    public class findByKnNumber {
        @Nested
        public class 正常系 {
            @Test
            public void knNumberが一致するデバイス情報を返す() {
                headers.add("Authorization", "Bearer test-token");
                ResponseEntity<InventoryDateResponse> userResponseResponseEntity = template.exchange("/api/v1/d42/device?kn-number=1", HttpMethod.GET, new HttpEntity<>(headers), InventoryDateResponse.class);
                assertThat(userResponseResponseEntity.getStatusCode().value()).isEqualTo(200);
                assertThat(userResponseResponseEntity.getBody().getDevicePk()).isEqualTo(1);
                assertThat(userResponseResponseEntity.getBody().getCustomerFk()).isEqualTo(1);
                assertThat(userResponseResponseEntity.getBody().getBuildingFk()).isEqualTo(1);
                assertThat(userResponseResponseEntity.getBody().getHardwareFk()).isEqualTo(1);
                assertThat(userResponseResponseEntity.getBody().getVendorFk()).isEqualTo(1);
            }
        }
        @Nested
        public class 異常系 {
            @Test
            public void headerのtokenが無効だと401レスポンスする() {
                headers.add("Authorization", "Bearer invalid-token");
                ResponseEntity<ErrorResponse> userResponseResponseEntity = template.exchange("/api/v1/d42/device?kn-number=1", HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);
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

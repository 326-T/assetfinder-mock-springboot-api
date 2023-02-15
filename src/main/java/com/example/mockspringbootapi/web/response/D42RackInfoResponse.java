package com.example.mockspringbootapi.web.response;

import lombok.*;


@Getter
@Builder
public class D42RackInfoResponse {
    private Integer rackPk;
    private String name;
    private Integer roomFk;
}

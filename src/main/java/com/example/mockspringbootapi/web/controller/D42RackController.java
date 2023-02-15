package com.example.mockspringbootapi.web.controller;

import com.example.mockspringbootapi.util.StringUtil;
import com.example.mockspringbootapi.web.response.D42RackInfoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/d42/rack")
public class D42RackController {

    @GetMapping
    public D42RackInfoResponse findByKnNumber(@RequestParam(value = "kn-number", required = false) String knNumber) {
        switch (StringUtil.hashString(knNumber)) {
            case 1:
                return D42RackInfoResponse.builder().rackPk(1).name("テストラック1").roomFk(1).build();
            case 2:
                return D42RackInfoResponse.builder().rackPk(2).name("テストラック2").roomFk(2).build();
            case 3:
                return D42RackInfoResponse.builder().rackPk(3).name("テストラック3").roomFk(3).build();
            case 4:
                return D42RackInfoResponse.builder().rackPk(4).name("テストラック4").roomFk(4).build();
            case 5:
                return D42RackInfoResponse.builder().rackPk(5).name("テストラック5").roomFk(5).build();
            default:
                return D42RackInfoResponse.builder().rackPk(10).name("テストラック10").roomFk(10).build();
        }
    }
}

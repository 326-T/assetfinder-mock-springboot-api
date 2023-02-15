package com.example.mockspringbootapi.web.controller;

import com.example.mockspringbootapi.util.StringUtil;
import com.example.mockspringbootapi.web.response.D42RackInfoResponse;
import com.example.mockspringbootapi.web.response.InventoryDateResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/d42/device")
public class D42DeviceController {

    @GetMapping
    public InventoryDateResponse findByKnNumber(@RequestParam(value = "kn-number", required = false) String knNumber) {
        switch (StringUtil.hashString(knNumber)) {
            case 1:
                return InventoryDateResponse.builder()
                        .devicePk(1).customerFk(1).buildingFk(1).roomFk(1).rackFk(1).hardwareFk(1).vendorFk(1)
                        .name("テストデバイス1").serialNo("11111").startAt("1").orientation("front").xpos("1").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-11111").assetNumber("asset-11111").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
            case 2:
                return InventoryDateResponse.builder()
                        .devicePk(2).customerFk(2).buildingFk(2).roomFk(2).rackFk(2).hardwareFk(2).vendorFk(2)
                        .name("テストデバイス2").serialNo("22222").startAt("2").orientation("front").xpos("2").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-22222").assetNumber("asset-22222").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
            case 3:
                return InventoryDateResponse.builder()
                        .devicePk(3).customerFk(3).buildingFk(3).roomFk(3).rackFk(3).hardwareFk(3).vendorFk(3)
                        .name("テストデバイス3").serialNo("33333").startAt("3").orientation("front").xpos("3").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-33333").assetNumber("asset-33333").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
            case 4:
                return InventoryDateResponse.builder()
                        .devicePk(4).customerFk(4).buildingFk(4).roomFk(4).rackFk(4).hardwareFk(4).vendorFk(4)
                        .name("テストデバイス4").serialNo("44444").startAt("4").orientation("front").xpos("4").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-44444").assetNumber("asset-44444").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
            case 5:
                return InventoryDateResponse.builder()
                        .devicePk(5).customerFk(5).buildingFk(5).roomFk(5).rackFk(5).hardwareFk(5).vendorFk(5)
                        .name("テストデバイス5").serialNo("55555").startAt("5").orientation("front").xpos("5").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-55555").assetNumber("asset-55555").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
            default:
                return InventoryDateResponse.builder()
                        .devicePk(10).customerFk(10).buildingFk(10).roomFk(10).rackFk(10).hardwareFk(10).vendorFk(10)
                        .name("テストデバイス10").serialNo("1010101010").startAt("10").orientation("front").xpos("10").statusOfUse("稼働").status("設置済み")
                        .knNumber("kn-1010101010").assetNumber("asset-1010101010").hardwareType("ネットワーク機器").pduWhere("above")
                        .build();
        }
    }
}

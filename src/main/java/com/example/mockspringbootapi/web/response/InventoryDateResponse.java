package com.example.mockspringbootapi.web.response;

import lombok.*;

@Getter
@Builder
public class InventoryDateResponse {
    private Integer devicePk;
    private Integer customerFk;
    private String customerName;
    private Integer buildingFk;
    private String buildingName;
    private Integer roomFk;
    private String roomName;
    private Integer rackFk;
    private String rackName;
    private Integer hardwareFk;
    private String hardwareName;
    private String name;
    private String serialNo;
    private String startAt;
    private String orientation;
    private String xpos;
    private String statusOfUse;
    private String status;
    private String knNumber;
    private String assetNumber;
    private Integer vendorFk;
    private String vendorName;
    private String hardwareType;
    private String pduWhere;
}

package com.wds.disaster_service.dto.request;

import lombok.Data;

@Data
public class DamageReportRequest {
    private Integer casualties;
    private Integer injuries;
    private Double economicLoss;
    private String damageDescription;
}

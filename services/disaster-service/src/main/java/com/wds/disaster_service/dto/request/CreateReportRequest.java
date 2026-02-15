package com.wds.disaster_service.dto.request;

import lombok.Data;

@Data
public class CreateReportRequest {
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private String locationName;
}

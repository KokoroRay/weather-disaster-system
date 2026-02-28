package com.wds.disaster_service.dto.request;

import lombok.Data;

@Data
public class SosRequestDto {

    private String message;
    private Double latitude;
    private Double longitude;
    private String contactPhone;
}

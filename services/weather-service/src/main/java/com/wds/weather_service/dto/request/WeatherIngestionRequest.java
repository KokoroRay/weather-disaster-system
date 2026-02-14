package com.wds.weather_service.dto.request;

import lombok.Data;

@Data
public class WeatherIngestionRequest {
    private String cityCode;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private String weatherCondition;
    private String alertLevel;
    private String icon;
}

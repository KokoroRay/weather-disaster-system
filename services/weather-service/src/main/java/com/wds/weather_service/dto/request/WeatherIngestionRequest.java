package com.wds.weather_service.dto.request;

import lombok.Data;

@Data
public class WeatherIngestionRequest {
    private String city;
    private String cityCode;
    private Double temperature;
    private Double feelsLike;
    private Double humidity;
    private Double windSpeed;
    private String windDirection;
    private Double pressure;
    private Double uvIndex;
    private Double precip;
    private String weatherCondition;
    private String icon;
    private String isDay;
    private String alertLevel;
}

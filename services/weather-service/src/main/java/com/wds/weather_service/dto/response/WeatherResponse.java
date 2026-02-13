package com.wds.weather_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherResponse {

    private String city;
    private String region;
    private double temp;
    private double humidity;
    private double windSpeed;
    private String condition;
    private String icon;
    private String alertLevel;
    private LocalDateTime updatedAt;
}

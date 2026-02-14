package com.wds.weather_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ForecastResponse {
    private LocalDate date;
    private double mapTemp;
    private double minTemp;
    private String condition;
    private String icon;
}

package com.wds.weather_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
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
    private String manualAlertMessage;
    private LocalDateTime recordedAt;
}

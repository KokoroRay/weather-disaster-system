package com.wds.weather_service.controller;

import com.wds.weather_service.entity.WeatherData;
import com.wds.weather_service.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/weather")
public class AdminWeatherController {

    private final WeatherService weatherService;

    public ResponseEntity<WeatherData> updateWeather(
            @PathVariable String cityCode,
            @RequestBody WeatherData weatherData ){
        return ResponseEntity.ok(weatherService.updateWeather(cityCode, weatherData));
    }
}

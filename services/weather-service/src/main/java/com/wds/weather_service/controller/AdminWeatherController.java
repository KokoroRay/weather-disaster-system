package com.wds.weather_service.controller;

import com.wds.weather_service.dto.request.WeatherIngestionRequest;
import com.wds.weather_service.entity.WeatherData;
import com.wds.weather_service.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/weather")
public class AdminWeatherController {

    private final WeatherService weatherService;

    @PostMapping("/update/{cityCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WeatherData> updateWeather(
            @PathVariable String cityCode,
            @RequestBody WeatherData weatherData ){
        return ResponseEntity.ok(weatherService.updateWeather(cityCode, weatherData));
    }

    @PostMapping("/ingest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> ingestWeatherData(
            @RequestBody WeatherIngestionRequest data) {
        weatherService.saveWeatherFromN8n(data);
        return ResponseEntity.ok("Weather data ingested successfully");
    }
}

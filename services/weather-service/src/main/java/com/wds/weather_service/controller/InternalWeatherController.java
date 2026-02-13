package com.wds.weather_service.controller;

import com.wds.weather_service.dto.request.WeatherIngestionRequest;
import com.wds.weather_service.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/weather")
@RequiredArgsConstructor
public class InternalWeatherController {

    private final WeatherService weatherService;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestData(@RequestBody WeatherIngestionRequest request) {
        weatherService.saveWeatherFromN8n(request);
        return ResponseEntity.ok("Received data for " + request.getCityCode());
    }
}

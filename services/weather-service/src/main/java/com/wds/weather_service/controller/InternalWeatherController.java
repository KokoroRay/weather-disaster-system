package com.wds.weather_service.controller;

import com.wds.weather_service.dto.request.WeatherIngestionRequest;
import com.wds.weather_service.service.WeatherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/weather")
@RequiredArgsConstructor
public class InternalWeatherController {

    private final WeatherService weatherService;
    private final tools.jackson.databind.ObjectMapper objectMapper;

    @PostMapping("/ingest")
    public ResponseEntity<java.util.Map<String, Object>> ingestData(@RequestBody tools.jackson.databind.JsonNode json) {
        if (json.isArray()) {
            List<WeatherIngestionRequest> requests = java.util.Arrays.asList(objectMapper.convertValue(json, WeatherIngestionRequest[].class));
            for (WeatherIngestionRequest request : requests) {
                weatherService.saveWeatherFromN8n(request);
            }
            return ResponseEntity.ok(java.util.Map.of("message", "Received " + requests.size() + " records"));
        } else {
            WeatherIngestionRequest request = objectMapper.convertValue(json, WeatherIngestionRequest.class);
            weatherService.saveWeatherFromN8n(request);
            return ResponseEntity.ok(java.util.Map.of("message", "Received 1 record"));
        }
    }

    @PostMapping("/ingest-forecast")
    public ResponseEntity<java.util.Map<String, Object>> ingestForecast(@RequestBody tools.jackson.databind.JsonNode json) {
        if (json.isArray()) {
            List<com.wds.weather_service.dto.request.ForecastIngestionRequest> requests = java.util.Arrays.asList(objectMapper.convertValue(json, com.wds.weather_service.dto.request.ForecastIngestionRequest[].class));
            for (com.wds.weather_service.dto.request.ForecastIngestionRequest request : requests) {
                weatherService.saveForecastFromN8n(request);
            }
            return ResponseEntity.ok(java.util.Map.of("message", "Received " + requests.size() + " forecast records"));
        } else {
            com.wds.weather_service.dto.request.ForecastIngestionRequest request = objectMapper.convertValue(json, com.wds.weather_service.dto.request.ForecastIngestionRequest.class);
            weatherService.saveForecastFromN8n(request);
            return ResponseEntity.ok(java.util.Map.of("message", "Received 1 forecast record"));
        }
    }
}

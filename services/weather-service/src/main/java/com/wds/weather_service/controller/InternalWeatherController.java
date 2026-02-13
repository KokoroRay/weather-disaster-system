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
    public ResponseEntity<String> ingestData(@RequestBody tools.jackson.databind.JsonNode json) {
        if (json.isArray()) {
            List<WeatherIngestionRequest> requests = java.util.Arrays.asList(objectMapper.convertValue(json, WeatherIngestionRequest[].class));
            for (WeatherIngestionRequest request : requests) {
                weatherService.saveWeatherFromN8n(request);
            }
            return ResponseEntity.ok("Received " + requests.size() + " records");
        } else {
            WeatherIngestionRequest request = objectMapper.convertValue(json, WeatherIngestionRequest.class);
            weatherService.saveWeatherFromN8n(request);
            return ResponseEntity.ok("Received 1 record");
        }
    }
}

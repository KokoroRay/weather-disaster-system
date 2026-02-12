package com.wds.weather_service.controller;

import com.wds.weather_service.entity.WeatherData;
import com.wds.weather_service.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public ResponseEntity<WeatherData> getCurrentWeather(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {
        if (city != null) {
            return ResponseEntity.ok(weatherService.getCurrentWeatherByCity(city));
        }  else if(lat != null && lon != null) {
            return ResponseEntity.ok(weatherService.getCurrentWeatherByGPS(lat, lon));
        }
        return ResponseEntity.badRequest().build();
    }
}

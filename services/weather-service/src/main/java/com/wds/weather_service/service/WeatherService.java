package com.wds.weather_service.service;

import com.wds.weather_service.dto.response.WeatherResponse;
import com.wds.weather_service.entity.Location;
import com.wds.weather_service.entity.WeatherData;
import com.wds.weather_service.repository.LocationRepository;
import com.wds.weather_service.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final LocationRepository locationRepository;
    private final WeatherDataRepository weatherDataRepository;

    public WeatherData getCurrentWeatherByCity(String cityCode){
        return weatherDataRepository.findTopByLocationCityCodeOrderByRecordedAtDesc(cityCode)
                .orElseThrow(() -> new RuntimeException("Weather data is not available for this city."));
    }

    public WeatherData getCurrentWeatherByGPS(Double lat, Double lon){
        Location nearestLocation = locationRepository.findNearestLocation(lat,lon)
                .orElseThrow(() -> new RuntimeException("Nearest location is not available for this city."));
        return weatherDataRepository.findTopByLocationCityCodeOrderByRecordedAtDesc(nearestLocation.getCityCode())
                .orElseThrow(() -> new RuntimeException("Weather data is not available for this city."));
    }
    public WeatherData updateWeather(String cityCode,WeatherData weatherData){
        Location location = locationRepository.findByCityCode(cityCode)
                .orElseThrow(() -> new RuntimeException("Location is not available for this city."));
        weatherData.setLocation(location);
        if (weatherData.getRecordedAt() == null){
            weatherData.setRecordedAt(LocalDateTime.now());
        }
        return weatherDataRepository.save(weatherData);
    }

}

package com.wds.weather_service.service;

import com.wds.weather_service.dto.response.WeatherResponse;
import com.wds.weather_service.entity.Location;
import com.wds.weather_service.entity.WeatherData;
import com.wds.weather_service.mapper.WeatherMapper;
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
    private final WeatherMapper weatherMapper;

    public WeatherResponse getCurrentWeatherByCity(String cityCode){
        WeatherData weatherData = weatherDataRepository.findTopByLocationCityCodeOrderByRecordedAtDesc(cityCode)
                .orElseThrow(() -> new RuntimeException("Weather data is not available for this city."));
        return weatherMapper.toResponse(weatherData);
    }

    public WeatherResponse getCurrentWeatherByGPS(Double lat, Double lon){
        Location nearestLocation = locationRepository.findNearestLocation(lat,lon)
                .orElseThrow(() -> new RuntimeException("Nearest location is not available for this city."));
        WeatherData weatherData = weatherDataRepository.findTopByLocationCityCodeOrderByRecordedAtDesc(nearestLocation.getCityCode())
                .orElseThrow(() -> new RuntimeException("Weather data is not available for this city."));
        return weatherMapper.toResponse(weatherData);
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

    public void saveWeatherFromN8n(com.wds.weather_service.dto.request.WeatherIngestionRequest request){
        Location location = locationRepository.findByCityCode(request.getCityCode())
                .orElseThrow(() -> new RuntimeException("City Code không tồn tại!"));
        
        WeatherData data = new WeatherData();
        data.setLocation(location);
        data.setTemperature(request.getTemperature());
        data.setHumidity(request.getHumidity());
        data.setWindSpeed(request.getWindSpeed());
        data.setWeatherCondition(request.getWeatherCondition());
        data.setAlertLevel(request.getAlertLevel());
        data.setRecordedAt(LocalDateTime.now());
        
        weatherDataRepository.save(data);
    }

}

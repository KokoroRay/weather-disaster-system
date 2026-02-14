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
    private final com.wds.weather_service.repository.WeatherForecastRepository weatherForecastRepository;
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
        String cityCode = request.getCityCode() != null ? request.getCityCode() : mapCityNameToCode(request.getCity());
        
        Location location = locationRepository.findByCityCode(cityCode)
                .orElseThrow(() -> new RuntimeException("City Code '" + cityCode + "' không tồn tại!"));
        
        WeatherData data = new WeatherData();
        data.setLocation(location);
        data.setTemperature(request.getTemperature());
        data.setFeelsLike(request.getFeelsLike());
        data.setHumidity(request.getHumidity());
        data.setWindSpeed(request.getWindSpeed());
        data.setWindDirection(request.getWindDirection());
        data.setPressure(request.getPressure());
        data.setUvIndex(request.getUvIndex());
        data.setPrecip(request.getPrecip());
        data.setWeatherCondition(request.getWeatherCondition());
        data.setIcon(request.getIcon());
        data.setIsDay(request.getIsDay());
        data.setAlertLevel(request.getAlertLevel());
        data.setRecordedAt(LocalDateTime.now());
        
        weatherDataRepository.save(data);
    }

    private String mapCityNameToCode(String cityName) {
        if (cityName == null) return null;
        Location location = locationRepository.findByCityName(cityName)
                .orElseThrow(() -> new RuntimeException("City '" + cityName + "' không tồn tại!"));
        return location.getCityCode();
    }

    public void saveForecastFromN8n(com.wds.weather_service.dto.request.ForecastIngestionRequest request) {
        Location location = locationRepository.findByCityCode(request.getCityCode())
                .orElseThrow(() -> new RuntimeException("City Code '" + request.getCityCode() + "' không tồn tại!"));
        
        com.wds.weather_service.entity.WeatherForecast forecast = new com.wds.weather_service.entity.WeatherForecast();
        forecast.setLocation(location);
        forecast.setForecastDate(java.time.LocalDate.parse(request.getForecastDate()));
        forecast.setTempMax(request.getTempMax());
        forecast.setTempMin(request.getTempMin());
        forecast.setUvIndex(request.getUvIndex());
        forecast.setWeatherCondition(request.getWeatherCondition());
        forecast.setIcon(request.getIcon());
        
        weatherForecastRepository.save(forecast);
    }

}

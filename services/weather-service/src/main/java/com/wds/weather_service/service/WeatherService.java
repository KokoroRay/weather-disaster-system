package com.wds.weather_service.service;


import com.wds.weather_service.dto.request.ForecastIngestionRequest;
import com.wds.weather_service.dto.request.WeatherIngestionRequest;
import com.wds.weather_service.dto.response.WeatherResponse;
import com.wds.weather_service.entity.WeatherData;

public interface WeatherService {
    WeatherResponse getCurrentWeatherByCity(String cityCode);
    WeatherResponse getCurrentWeatherByGPS(Double lat, Double lon);
    WeatherData updateWeather(String cityCode, WeatherData weatherData);
    void saveWeatherFromN8n(WeatherIngestionRequest request);
    void saveForecastFromN8n(ForecastIngestionRequest request);

}

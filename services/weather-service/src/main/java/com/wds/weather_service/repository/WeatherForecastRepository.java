package com.wds.weather_service.repository;

import com.wds.weather_service.entity.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {
}

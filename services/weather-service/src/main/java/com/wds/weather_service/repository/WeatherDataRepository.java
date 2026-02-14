package com.wds.weather_service.repository;

import com.wds.weather_service.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    Optional<WeatherData> findTopByLocationCityCodeOrderByRecordedAtDesc(String cityCode);
}

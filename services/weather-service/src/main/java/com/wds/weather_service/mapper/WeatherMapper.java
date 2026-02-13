package com.wds.weather_service.mapper;

import com.wds.weather_service.dto.response.WeatherResponse;
import com.wds.weather_service.entity.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherResponse toResponse(WeatherData entity){
        if(entity == null){ return null;}
        return WeatherResponse.builder()
                .city(entity.getLocation().getCityName())
                .region(entity.getLocation().getRegion())
                .temp(entity.getTemperature())
                .humidity(entity.getHumidity())
                .windSpeed(entity.getWindSpeed())
                .condition(entity.getWeatherCondition())
                .icon(entity.getIcon())
                .alertLevel(entity.getAlertLevel())
                .updatedAt(entity.getRecordedAt())
                .build();
    }
}

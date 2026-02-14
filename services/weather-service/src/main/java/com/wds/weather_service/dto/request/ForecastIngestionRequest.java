package com.wds.weather_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastIngestionRequest {
    @JsonProperty("city_code")
    private String cityCode;
    
    @JsonProperty("forecast_date")
    private String forecastDate;
    
    @JsonProperty("temp_max")
    private Double tempMax;
    
    @JsonProperty("temp_min")
    private Double tempMin;
    
    @JsonProperty("uv_index")
    private Double uvIndex;
    
    @JsonProperty("condition_text")
    private String weatherCondition;
    
    @JsonProperty("icon_url")
    private String icon;
}

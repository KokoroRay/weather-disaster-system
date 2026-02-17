package com.wds.disaster_service.dto.request;

import com.wds.disaster_service.entity.constants.AlertLevel;
import lombok.Data;

import java.util.List;

@Data
public class UpdateDisasterRequest {
    private AlertLevel alertLevel;
    private List<String> affectedLocations;
    private Double centerLatitude;
    private Double centerLongitude;
    private String description;
}

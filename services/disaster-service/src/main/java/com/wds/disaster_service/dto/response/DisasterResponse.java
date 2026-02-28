package com.wds.disaster_service.dto.response;

import com.wds.disaster_service.entity.DisasterEvent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DisasterResponse {
    private Long id;
    private String title;
    private String description;
    private String alertLevel;
    private String typeName;
    private String iconUrl;
    private List<String> affectedLocations;
    private LocalDateTime startTime;
    private String sourceUrl;
    private Double lat;
    private Double lon;


    public static DisasterResponse fromEntity(DisasterEvent disasterEvent) {
        DisasterResponse res = new DisasterResponse();
        res.setId(disasterEvent.getId());
        res.setTitle(disasterEvent.getTitle());
        res.setDescription(disasterEvent.getDescription());
        res.setAlertLevel(disasterEvent.getAlertLevel().name());
        res.setAffectedLocations(disasterEvent.getAffectedLocations());
        res.setStartTime(disasterEvent.getStartTime());
        res.setSourceUrl(disasterEvent.getSourceUrl());
        res.setLat(disasterEvent.getCenterLatitude());
        res.setLon(disasterEvent.getCenterLongitude());
        if (disasterEvent.getType() != null) {
            res.setTypeName(disasterEvent.getType().getName());
            res.setIconUrl(disasterEvent.getType().getIconUrl());
        }
        return res;
    }
}

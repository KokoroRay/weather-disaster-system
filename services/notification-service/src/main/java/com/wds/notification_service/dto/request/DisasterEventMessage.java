package com.wds.notification_service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DisasterEventMessage {

    private String title;
    private String alertLevel;
    private List<String> affectedLocations;
}

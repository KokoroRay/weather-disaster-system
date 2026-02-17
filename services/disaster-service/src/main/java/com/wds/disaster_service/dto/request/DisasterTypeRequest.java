package com.wds.disaster_service.dto.request;

import lombok.Data;

@Data
public class DisasterTypeRequest {
    private String name;
    private String code;
    private String iconUrl;
}

package com.wds.notification_service.dto.request;


import lombok.Data;

@Data
public class AddLocationRequest {

    private String locationCode;
    private String locationName;
}

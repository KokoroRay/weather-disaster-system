package com.wds.notification_service.dto.response;


import com.wds.notification_service.entity.NotificationSetting;
import com.wds.notification_service.entity.UserLocation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLocationResponse {

    private Long id;
    private String locationCode;
    private String locationName;

    public static UserLocationResponse fromEntity(UserLocation e) {
        return builder().id(e.getId()).locationCode(e.getLocationCode()).locationName(e.getLocationName()).build();
    }
}

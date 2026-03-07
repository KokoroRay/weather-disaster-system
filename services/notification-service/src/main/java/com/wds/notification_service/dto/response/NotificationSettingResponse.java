package com.wds.notification_service.dto.response;


import com.wds.notification_service.entity.NotificationSetting;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationSettingResponse {

    private boolean receiveWeatherUpdates;
    private boolean receiveDisasterAlerts;

    public static NotificationSettingResponse fromEntity(NotificationSetting e) {
        return builder().receiveWeatherUpdates(e.isReceiveWeatherUpdates())
                .receiveDisasterAlerts(e.isReceiveDisasterAlerts()).build();
    }
}

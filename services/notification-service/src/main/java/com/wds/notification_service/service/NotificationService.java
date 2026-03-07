package com.wds.notification_service.service;

import com.wds.notification_service.dto.request.AddLocationRequest;
import com.wds.notification_service.dto.request.DisasterEventMessage;
import com.wds.notification_service.dto.response.NotificationHistoryResponse;
import com.wds.notification_service.dto.response.NotificationSettingResponse;
import com.wds.notification_service.dto.response.UserLocationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {
    UserLocationResponse addLocation(Long userId, AddLocationRequest req);
    List<UserLocationResponse> getMyLocations(Long userId);
    void removeLocation(Long userId, String locationCode);
    List<NotificationHistoryResponse> getMyNotifications(Long userId);
    void markAsRead(Long notificationId);
    NotificationSettingResponse getMySettings(Long userId);
    NotificationSettingResponse updateSettings(Long userId, boolean weather, boolean disaster);
    void consumeDisasterAlert(DisasterEventMessage event);

}
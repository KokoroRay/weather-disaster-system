package com.wds.notification_service.service.impl;

import com.wds.notification_service.dto.request.AddLocationRequest;
import com.wds.notification_service.dto.request.DisasterEventMessage;
import com.wds.notification_service.dto.response.NotificationHistoryResponse;
import com.wds.notification_service.dto.response.NotificationSettingResponse;
import com.wds.notification_service.dto.response.UserLocationResponse;
import com.wds.notification_service.entity.NotificationHistory;
import com.wds.notification_service.entity.NotificationSetting;
import com.wds.notification_service.entity.UserLocation;
import com.wds.notification_service.repository.NotificationHistoryRepository;
import com.wds.notification_service.repository.NotificationSettingRepository;
import com.wds.notification_service.repository.UserLocationRepository;
import com.wds.notification_service.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserLocationRepository locationRepository;
    private final NotificationHistoryRepository notificationHistoryRepository;
    private final NotificationSettingRepository notificationSettingRepository;

    @Override
    public UserLocationResponse addLocation(Long userId, AddLocationRequest req) {
        UserLocation loc = new UserLocation();
        loc.setUserId(userId);
        loc.setLocationCode(req.getLocationCode());
        loc.setLocationName(req.getLocationName());
        return UserLocationResponse.fromEntity(locationRepository.save(loc));
    }

    @Override
    public List<UserLocationResponse> getMyLocations(Long userId) {
        return locationRepository.findByUserId(userId).stream().map(UserLocationResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeLocation(Long userId, String locationCode) {
        locationRepository.deleteByUserIdAndLocationCode(userId, locationCode);
    }

    @Override
    public List<NotificationHistoryResponse> getMyNotifications(Long userId) {
        return notificationHistoryRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(NotificationHistoryResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {
        NotificationHistory notificationHistory = notificationHistoryRepository.findById(notificationId).orElseThrow();
        notificationHistory.setRead(true);
        notificationHistoryRepository.save(notificationHistory);
    }

    @Override
    public NotificationSettingResponse getMySettings(Long userId) {
        return NotificationSettingResponse.fromEntity(notificationSettingRepository.findByUserId(userId).orElse(new NotificationSetting()));
    }

    @Override
    public NotificationSettingResponse updateSettings(Long userId, boolean weather, boolean disaster) {
        NotificationSetting setting = notificationSettingRepository.findByUserId(userId).orElse(new NotificationSetting());
        setting.setUserId(userId);
        setting.setReceiveWeatherUpdates(weather);
        setting.setReceiveDisasterAlerts(disaster);
        return NotificationSettingResponse.fromEntity(notificationSettingRepository.save(setting));
    }

    @Override
    @KafkaListener(topics = "disaster_alerts", groupId = "notification-group")
    public void consumeDisasterAlert(DisasterEventMessage event) {
        if(event.getAffectedLocations() == null || event.getAffectedLocations().isEmpty()) return;
        List<UserLocation> affectedUsers = locationRepository.findByLocationCodeIn(event.getAffectedLocations());
        List<Long> userIds = affectedUsers.stream().map(UserLocation::getUserId).distinct().collect(Collectors.toList());

        for (Long userId : userIds) {
            NotificationSetting setting = notificationSettingRepository.findByUserId(userId).orElse(new NotificationSetting());
            if(setting.isReceiveDisasterAlerts()) {
                NotificationHistory notification = new NotificationHistory();
                notification.setId(userId);
                notification.setTitle("ẢNH BÁO THIÊN TAI: " + event.getAlertLevel());
                notification.setMessage(event.getTitle());
                notification.setType("DISASTER");
                notificationHistoryRepository.save(notification);
                System.out.println(">>> Đã tạo thông báo khẩn cho UserID: " + userId);
            }
        }
    }
}

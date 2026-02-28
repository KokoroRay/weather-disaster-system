package com.wds.notification_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notification_settings")
@Data
public class NotificationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long userId;

    private boolean receiveWeatherUpdates = true;
    private boolean receiveDisasterAlerts = true;
}

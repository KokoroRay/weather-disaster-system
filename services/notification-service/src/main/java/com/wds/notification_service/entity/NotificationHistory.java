package com.wds.notification_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_histories")
@Data
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String title;
    private String message;
    private String type;

    private boolean isRead = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    }

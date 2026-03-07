package com.wds.notification_service.dto.response;


import com.wds.notification_service.entity.NotificationHistory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationHistoryResponse {

    private Long id;
    private String title;
    private String message;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;
    public static NotificationHistoryResponse fromEntity(NotificationHistory e) {
        return builder().id(e.getId()).title(e.getTitle()).message(e.getMessage())
                .type(e.getType()).isRead(e.isRead()).createdAt(e.getCreatedAt()).build();
    }
}

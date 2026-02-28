package com.wds.disaster_service.entity;


import com.wds.disaster_service.entity.constants.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_report")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String title;
    private String description;

    @ElementCollection
    private List<String> imageUrls;
    private Double latitude;
    private Double longitude;
    private String locationName;
    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now();

}

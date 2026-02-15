package com.wds.disaster_service.entity;


import com.wds.disaster_service.entity.constants.AlertLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "disaster_event")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisasterEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String sourceUrl;
    @Enumerated(EnumType.STRING)
    private AlertLevel alertLevel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isActive = true;

    @ElementCollection
    private List<String> affectedLocations;
    private Double centerLatitude;
    private Double centerLongitude;
    @ManyToOne
    private DisasterType type;

}

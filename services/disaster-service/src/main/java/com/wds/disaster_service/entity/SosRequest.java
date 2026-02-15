package com.wds.disaster_service.entity;


import com.wds.disaster_service.entity.constants.SosStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sos_request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String message;
    private Double latitude;
    private Double longitude;
    private String contactPhone;
    @Enumerated(EnumType.STRING)
    private SosStatus status = SosStatus.OPEN;
    private LocalDateTime createdAt = LocalDateTime.now();
}

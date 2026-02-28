package com.wds.notification_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_locations")
@Data
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String locationCode;
    private String locationName;

}

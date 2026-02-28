package com.wds.disaster_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disaster_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisasterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String code;
    private String iconUrl;
}

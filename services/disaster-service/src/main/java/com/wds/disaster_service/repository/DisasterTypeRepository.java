package com.wds.disaster_service.repository;

import com.wds.disaster_service.entity.DisasterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisasterTypeRepository extends JpaRepository<DisasterType, Long> {
    Optional<DisasterType> findByCode(String code);
}


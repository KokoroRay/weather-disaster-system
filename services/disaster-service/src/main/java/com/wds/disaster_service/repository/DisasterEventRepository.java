package com.wds.disaster_service.repository;

import com.wds.disaster_service.entity.DisasterEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisasterEventRepository extends JpaRepository<DisasterEvent, Long> {
    List<DisasterEvent> findByIsActiveTrueOrderByStartTimeDesc();
    List<DisasterEvent> findByTitleContainingIgnoreCase(String keyword);
}

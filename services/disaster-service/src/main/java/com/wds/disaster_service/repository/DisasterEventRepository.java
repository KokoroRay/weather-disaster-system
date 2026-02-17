package com.wds.disaster_service.repository;

import com.wds.disaster_service.entity.DisasterEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisasterEventRepository extends JpaRepository<DisasterEvent, Long> {
    List<DisasterEvent> findByIsActiveTrueOrderByStartTimeDesc();
    List<DisasterEvent> findByTitleContainingIgnoreCase(String keyword);
    List<DisasterEvent> findByTypeIdOrderByStartTimeDesc(Long typeId);
    
    @Query("SELECT e FROM DisasterEvent e WHERE e.centerLatitude IS NOT NULL AND e.centerLongitude IS NOT NULL " +
           "AND (6371 * acos(cos(radians(:lat)) * cos(radians(e.centerLatitude)) * " +
           "cos(radians(e.centerLongitude) - radians(:lon)) + sin(radians(:lat)) * " +
           "sin(radians(e.centerLatitude)))) <= :radius ORDER BY e.startTime DESC")
    List<DisasterEvent> findByLocationWithinRadius(@Param("lat") Double lat, 
                                                    @Param("lon") Double lon, 
                                                    @Param("radius") Double radius);
}

package com.wds.notification_service.repository;

import com.wds.notification_service.entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    List<UserLocation> findByUserId(Long userId);
    List<UserLocation> findByLocationCodeIn(List<String> locationCodes);
    void deleteByUserIdAndLocationCode(Long userId, String locationCode);

}

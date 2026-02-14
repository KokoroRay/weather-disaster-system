package com.wds.weather_service.repository;

import com.wds.weather_service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityCode(String cityCode);
    Optional<Location> findByCityName(String cityName);

    @Query(value = "SELECT * FROM locations l " +
            "ORDER BY ((l.latitude - :lat) * (l.latitude - :lat) + " +
            "(l.longitude - :lon) * (l.longitude - :lon)) ASC LIMIT 1",
            nativeQuery = true)
    Optional<Location> findNearestLocation(@Param("lat") double lat, @Param("lon") double lon);
}

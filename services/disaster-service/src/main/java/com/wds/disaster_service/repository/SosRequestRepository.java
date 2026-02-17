package com.wds.disaster_service.repository;

import com.wds.disaster_service.dto.request.SosRequestDto;
import com.wds.disaster_service.entity.SosRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SosRequestRepository extends JpaRepository<SosRequest, Long> {
}

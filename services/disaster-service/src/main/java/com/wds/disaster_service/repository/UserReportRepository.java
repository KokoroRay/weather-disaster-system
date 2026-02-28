package com.wds.disaster_service.repository;

import com.wds.disaster_service.entity.UserReport;
import com.wds.disaster_service.entity.constants.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    List<UserReport> findByUserIdOrderByCreatedAtDesc (Long userId);
    List<UserReport> findByStatusOrderByCreatedAtDesc(ReportStatus status);
}

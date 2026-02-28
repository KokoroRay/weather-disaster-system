package com.wds.disaster_service.service;

import com.wds.disaster_service.dto.request.*;
import com.wds.disaster_service.dto.response.DisasterResponse;
import com.wds.disaster_service.entity.DisasterType;
import com.wds.disaster_service.entity.SosRequest;
import com.wds.disaster_service.entity.UserReport;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DisasterService {
    void ingestFromN8n(DisasterIngestRequest req);
    List<DisasterResponse> getActiveDisasters();
    List<DisasterResponse> getDisastersByType(Long typeId);
    List<DisasterResponse> getDisasterHistory(Double lat, Double lon, Double radius);
    UserReport createReport(Long id, CreateReportRequest req, List<MultipartFile> files);
    List<UserReport> getUserReports(Long userId);
    UserReport updateReport(Long userId, Long reportId, UpdateReportRequest req);
    void deleteReport(Long userId, Long reportId);
    SosRequest sendSos (Long id, SosRequestDto req);
    void approveReport(Long reportId, boolean isApproved);
    List<UserReport> getAllReports();
    void endEvent(Long eventId);
    void updateDisaster(Long eventId, UpdateDisasterRequest req);
    void recordDamage(Long eventId, DamageReportRequest req);
    List<DisasterType> getAllTypes();
    DisasterType createType(DisasterTypeRequest req);
    DisasterType updateType(Long typeId, DisasterTypeRequest req);
    void deleteType(Long typeId);
}

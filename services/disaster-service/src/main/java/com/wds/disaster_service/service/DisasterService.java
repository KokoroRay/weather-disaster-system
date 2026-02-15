package com.wds.disaster_service.service;

import com.wds.disaster_service.dto.request.CreateReportRequest;
import com.wds.disaster_service.dto.request.DisasterIngestRequest;
import com.wds.disaster_service.dto.request.SosRequest;
import com.wds.disaster_service.dto.response.DisasterResponse;
import com.wds.disaster_service.entity.UserReport;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DisasterService {
    void ingestFromN8n(DisasterIngestRequest req);
    void ingestFromWeatherApi();
    List<DisasterResponse> getActiveDisasters();
    UserReport createReport(Long id, CreateReportRequest req, List<MultipartFile> files);
    SosRequest sendSos (Long id, SosRequest req);
    void approveReport(Long reportId, boolean isApproved);
    void endEvent(Long eventId);)
}

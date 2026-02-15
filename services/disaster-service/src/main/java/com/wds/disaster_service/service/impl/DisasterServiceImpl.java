package com.wds.disaster_service.service.impl;

import com.wds.disaster_service.dto.request.CreateReportRequest;
import com.wds.disaster_service.dto.request.DisasterIngestRequest;
import com.wds.disaster_service.dto.request.SosRequest;
import com.wds.disaster_service.dto.response.DisasterResponse;
import com.wds.disaster_service.entity.UserReport;
import com.wds.disaster_service.service.DisasterService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DisasterServiceImpl implements DisasterService {
    @Override
    public void ingestFromN8n(DisasterIngestRequest req) {

    }

    @Override
    public void ingestFromWeatherApi() {

    }

    @Override
    public List<DisasterResponse> getActiveDisasters() {
        return List.of();
    }

    @Override
    public UserReport createReport(Long id, CreateReportRequest req, List<MultipartFile> files) {
        return null;
    }

    @Override
    public SosRequest sendSos(Long id, SosRequest req) {
        return null;
    }

    @Override
    public void approveReport(Long reportId, boolean isApproved) {

    }

    @Override
    public void endEvent(Long eventId) {

    }
}

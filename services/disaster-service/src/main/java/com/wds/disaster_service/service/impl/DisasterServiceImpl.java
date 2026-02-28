package com.wds.disaster_service.service.impl;

import com.wds.disaster_service.dto.request.*;
import com.wds.disaster_service.dto.response.DisasterResponse;
import com.wds.disaster_service.entity.DisasterEvent;
import com.wds.disaster_service.entity.DisasterType;
import com.wds.disaster_service.entity.SosRequest;
import com.wds.disaster_service.entity.UserReport;
import com.wds.disaster_service.entity.constants.AlertLevel;
import com.wds.disaster_service.entity.constants.ReportStatus;
import com.wds.disaster_service.entity.constants.SosStatus;
import com.wds.disaster_service.repository.DisasterEventRepository;
import com.wds.disaster_service.repository.DisasterTypeRepository;
import com.wds.disaster_service.repository.SosRequestRepository;
import com.wds.disaster_service.repository.UserReportRepository;
import com.wds.disaster_service.service.CloudinaryService;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisasterServiceImpl implements DisasterService {

    private final DisasterEventRepository disasterEventRepository;
    private final DisasterTypeRepository disasterTypeRepository;
    private final UserReportRepository userReportRepository;
    private final SosRequestRepository sosRequestRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public void ingestFromN8n(DisasterIngestRequest req) {
        DisasterEvent event = new DisasterEvent();
        event.setTitle(req.getTitle());
        event.setDescription(req.getDescription());
        event.setSourceUrl(req.getUrl());
        event.setAffectedLocations(req.getAffectedLocations());
        event.setStartTime(req.getStartTime() != null ? req.getStartTime() : LocalDateTime.now());

        DisasterType type = disasterTypeRepository.findByCode(req.getDisasterType().toUpperCase())
                .orElseGet(() -> disasterTypeRepository.findByCode("OTHER").orElse(null));
        event.setType(type);
        try {
            event.setAlertLevel(AlertLevel.valueOf(req.getAlertLevel().toUpperCase()));
        } catch (Exception e) {
            event.setAlertLevel(AlertLevel.GREEN);
        }
        disasterEventRepository.save(event);
    }

    @Override
    public List<DisasterResponse> getActiveDisasters() {
        return disasterEventRepository.findByIsActiveTrueOrderByStartTimeDesc()
                .stream().map(DisasterResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<DisasterResponse> getDisastersByType(Long typeId) {
        return disasterEventRepository.findByTypeIdOrderByStartTimeDesc(typeId)
                .stream().map(DisasterResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<DisasterResponse> getDisasterHistory(Double lat, Double lon, Double radius) {
        return disasterEventRepository.findByLocationWithinRadius(lat, lon, radius)
                .stream().map(DisasterResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UserReport createReport(Long id, CreateReportRequest req, List<MultipartFile> files) {
        UserReport report = new UserReport();
        report.setUserId(id);
        report.setTitle(req.getTitle());
        report.setDescription(req.getDescription());
        report.setLatitude(req.getLatitude());
        report.setLongitude(req.getLongitude());
        report.setLocationName(req.getLocationName());
        report.setStatus(ReportStatus.PENDING);

        List<String> imageUrl = new ArrayList<>();
        if(files != null) {
            for (MultipartFile file : files) {
                imageUrl.add(cloudinaryService.uploadFile(file));
            }
        }
        report.setImageUrls(imageUrl);
        return userReportRepository.save(report);
    }

    @Override
    public List<UserReport> getUserReports(Long userId) {
        return userReportRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public UserReport updateReport(Long userId, Long reportId, UpdateReportRequest req) {
        UserReport report = userReportRepository.findById(reportId).orElseThrow();
        if(!report.getUserId().equals(userId)) throw new RuntimeException("Unauthorized");
        report.setTitle(req.getTitle());
        report.setDescription(req.getDescription());
        report.setLatitude(req.getLatitude());
        report.setLongitude(req.getLongitude());
        report.setLocationName(req.getLocationName());
        return userReportRepository.save(report);
    }

    @Override
    public void deleteReport(Long userId, Long reportId) {
        UserReport report = userReportRepository.findById(reportId).orElseThrow();
        if(!report.getUserId().equals(userId)) throw new RuntimeException("Unauthorized");
        userReportRepository.delete(report);
    }

    @Override
    public SosRequest sendSos(Long id, SosRequestDto req) {
        SosRequest sosRequest = new SosRequest();
        sosRequest.setUserId(id);
        sosRequest.setMessage(req.getMessage());
        sosRequest.setLatitude(req.getLatitude());
        sosRequest.setLongitude(req.getLongitude());
        sosRequest.setContactPhone(req.getContactPhone());
        sosRequest.setStatus(SosStatus.OPEN);
        return sosRequestRepository.save(sosRequest);
    }

    @Override
    public void approveReport(Long reportId, boolean isApproved) {
        UserReport report = userReportRepository.findById(reportId).orElseThrow();
        report.setStatus(isApproved ? ReportStatus.APPROVED : ReportStatus.REJECTED);
        userReportRepository.save(report);
    }

    @Override
    public List<UserReport> getAllReports() {
        return userReportRepository.findAll();
    }

    @Override
    public void endEvent(Long eventId) {
        DisasterEvent event = disasterEventRepository.findById(eventId).orElseThrow();
        event.setActive(false);
        event.setEndTime(LocalDateTime.now());
        disasterEventRepository.save(event);
    }

    @Override
    public void updateDisaster(Long eventId, UpdateDisasterRequest req) {
        DisasterEvent event = disasterEventRepository.findById(eventId).orElseThrow();
        if(req.getAlertLevel() != null) event.setAlertLevel(req.getAlertLevel());
        if(req.getAffectedLocations() != null) event.setAffectedLocations(req.getAffectedLocations());
        if(req.getCenterLatitude() != null) event.setCenterLatitude(req.getCenterLatitude());
        if(req.getCenterLongitude() != null) event.setCenterLongitude(req.getCenterLongitude());
        if(req.getDescription() != null) event.setDescription(req.getDescription());
        disasterEventRepository.save(event);
    }

    @Override
    public void recordDamage(Long eventId, DamageReportRequest req) {
        DisasterEvent event = disasterEventRepository.findById(eventId).orElseThrow();
        String damage = String.format("Casualties: %d, Injuries: %d, Loss: %.2f, Details: %s",
                req.getCasualties(), req.getInjuries(), req.getEconomicLoss(), req.getDamageDescription());
        event.setDescription(event.getDescription() + "\n\nDamage Report: " + damage);
        disasterEventRepository.save(event);
    }

    @Override
    public List<DisasterType> getAllTypes() {
        return disasterTypeRepository.findAll();
    }

    @Override
    public DisasterType createType(DisasterTypeRequest req) {
        DisasterType type = DisasterType.builder()
                .name(req.getName())
                .code(req.getCode())
                .iconUrl(req.getIconUrl())
                .build();
        return disasterTypeRepository.save(type);
    }

    @Override
    public DisasterType updateType(Long typeId, DisasterTypeRequest req) {
        DisasterType type = disasterTypeRepository.findById(typeId).orElseThrow();
        type.setName(req.getName());
        type.setCode(req.getCode());
        type.setIconUrl(req.getIconUrl());
        return disasterTypeRepository.save(type);
    }

    @Override
    public void deleteType(Long typeId) {
        disasterTypeRepository.deleteById(typeId);
    }
}

package com.wds.disaster_service.controller;


import com.wds.disaster_service.dto.request.CreateReportRequest;
import com.wds.disaster_service.dto.request.SosRequestDto;
import com.wds.disaster_service.dto.request.UpdateReportRequest;
import com.wds.disaster_service.entity.SosRequest;
import com.wds.disaster_service.entity.UserReport;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserActionController {

    private final DisasterService disasterService;

    // UC10.1: Xem danh sách đã báo cáo
    @GetMapping("/reports")
    public ResponseEntity<List<UserReport>> getMyReports(){
        Long userId = 1L;
        return ResponseEntity.ok(disasterService.getUserReports(userId));
    }

    // UC10.2: Tạo báo cáo
    @PostMapping(value = "/reports", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserReport> createReport(
            @RequestPart("data") CreateReportRequest req,
            @RequestPart(value = "images", required = false) List<MultipartFile> images){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = 1L;
        return ResponseEntity.ok(disasterService.createReport(userId,req,images));
    }

    // UC10.3: Sửa báo cáo
    @PutMapping("/reports/{reportId}")
    public ResponseEntity<UserReport> updateReport(
            @PathVariable Long reportId,
            @RequestBody UpdateReportRequest req){
        Long userId = 1L;
        return ResponseEntity.ok(disasterService.updateReport(userId, reportId, req));
    }

    // UC10.4: Xóa báo cáo
    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long reportId){
        Long userId = 1L;
        disasterService.deleteReport(userId, reportId);
        return ResponseEntity.noContent().build();
    }

    // UC11: Gửi yêu cầu cứu trợ khẩn cấp (SOS)
    @PostMapping("/sos")
    public ResponseEntity<SosRequest> sendSos(@RequestBody SosRequestDto requestDto){
        Long userId = 1L;
        return ResponseEntity.ok(disasterService.sendSos(userId, requestDto));
    }
}

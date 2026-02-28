package com.wds.disaster_service.controller;


import com.wds.disaster_service.dto.request.DamageReportRequest;
import com.wds.disaster_service.dto.request.UpdateDisasterRequest;
import com.wds.disaster_service.entity.UserReport;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/disaster")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDisasterController {
    private final DisasterService disasterService;

    // UC19.1: Xem danh sách báo cáo
    @GetMapping("/reports")
    public ResponseEntity<List<UserReport>> getAllReports(){
        return ResponseEntity.ok(disasterService.getAllReports());
    }

    // UC19.2: Duyệt/Từ chối báo cáo
    @PutMapping("/reports/{reportId}/status")
    public ResponseEntity<String> approveReport(
            @PathVariable Long reportId, 
            @RequestParam boolean isApproved){
        disasterService.approveReport(reportId,isApproved);
        return ResponseEntity.ok("Approved");
    }

    // UC17.2 & UC17.3: Cập nhật mức độ & khu vực ảnh hưởng
    @PutMapping("/events/{eventId}")
    public ResponseEntity<String> updateDisaster(
            @PathVariable Long eventId,
            @RequestBody UpdateDisasterRequest req){
        disasterService.updateDisaster(eventId, req);
        return ResponseEntity.ok("Updated");
    }

    // UC17.4: Kết thúc sự kiện thiên tai
    @PutMapping("/events/{eventId}/end")
    public ResponseEntity<String> endEvent(@PathVariable Long eventId){
        disasterService.endEvent(eventId);
        return ResponseEntity.ok("Ended");
    }

    // UC17.5: Ghi nhận thiệt hại
    @PostMapping("/events/{eventId}/damage")
    public ResponseEntity<String> recordDamage(
            @PathVariable Long eventId,
            @RequestBody DamageReportRequest req){
        disasterService.recordDamage(eventId, req);
        return ResponseEntity.ok("Damage recorded");
    }
}

package com.wds.notification_service.controller;

import com.wds.notification_service.dto.request.AddLocationRequest;
import com.wds.notification_service.dto.response.NotificationHistoryResponse;
import com.wds.notification_service.dto.response.NotificationSettingResponse;
import com.wds.notification_service.dto.response.UserLocationResponse;
import com.wds.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.RemoteRef;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    private Long getCurrentUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return 1L;
    }

    @PostMapping("/locations")
    public ResponseEntity<UserLocationResponse> addLocation(@RequestBody AddLocationRequest request) {
        return ResponseEntity.ok(service.addLocation(getCurrentUserId(), request));

    }

    @GetMapping("/locations")
    public ResponseEntity<List<UserLocationResponse>> getLocations() {
        return ResponseEntity.ok(service.getMyLocations(getCurrentUserId()));

    }

    @DeleteMapping("/locations/{code}")
    public ResponseEntity<String> removeLocation(@PathVariable String code) {
        service.removeLocation(getCurrentUserId(), code);
        return ResponseEntity.ok("Đã xóa");
    }

    @GetMapping("/history")
    public ResponseEntity<List<NotificationHistoryResponse>> getHistory() {
        return ResponseEntity.ok(service.getMyNotifications(getCurrentUserId()));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
        return ResponseEntity.ok("Đã đọc");
    }

    @GetMapping("/settings")
    public ResponseEntity<NotificationSettingResponse> getSettings() {
        return ResponseEntity.ok(service.getMySettings(getCurrentUserId()));

    }

    @PutMapping("/settings")
    public ResponseEntity<NotificationSettingResponse> updateSettings(@RequestParam boolean weather, @RequestParam boolean disaster) {
        return ResponseEntity.ok(service.updateSettings(getCurrentUserId(), weather, disaster));

    }
}

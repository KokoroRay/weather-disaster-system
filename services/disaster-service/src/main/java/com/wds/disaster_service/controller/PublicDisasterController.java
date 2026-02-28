package com.wds.disaster_service.controller;


import com.wds.disaster_service.dto.response.DisasterResponse;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disaster")
@RequiredArgsConstructor
public class PublicDisasterController {

    private final DisasterService disasterService;
    
    // UC3.1: Xem danh sách thiên tai xảy ra
    @GetMapping("/active")
    public ResponseEntity<List<DisasterResponse>> getActive(){
        return ResponseEntity.ok(disasterService.getActiveDisasters());
    }
    
    // UC3.3: Lọc theo loại
    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<DisasterResponse>> getByType(@PathVariable Long typeId){
        return ResponseEntity.ok(disasterService.getDisastersByType(typeId));
    }
    
    // UC3.4: Lịch sử thiên tai tại khu vực
    @GetMapping("/history")
    public ResponseEntity<List<DisasterResponse>> getHistory(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam(defaultValue = "50.0") Double radius){
        return ResponseEntity.ok(disasterService.getDisasterHistory(lat, lon, radius));
    }
}

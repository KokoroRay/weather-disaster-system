package com.wds.disaster_service.controller;

import com.wds.disaster_service.dto.request.DisasterTypeRequest;
import com.wds.disaster_service.entity.DisasterType;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disaster-types")
@RequiredArgsConstructor
public class DisasterTypeController {
    
    private final DisasterService disasterService;

    // UC16.1: Xem loại thiên tai
    @GetMapping
    public ResponseEntity<List<DisasterType>> getAllTypes(){
        return ResponseEntity.ok(disasterService.getAllTypes());
    }

    // UC16.2: Thêm loại thiên tai
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DisasterType> createType(@RequestBody DisasterTypeRequest req){
        return ResponseEntity.ok(disasterService.createType(req));
    }

    // UC16.3: Sửa loại thiên tai
    @PutMapping("/{typeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DisasterType> updateType(
            @PathVariable Long typeId,
            @RequestBody DisasterTypeRequest req){
        return ResponseEntity.ok(disasterService.updateType(typeId, req));
    }

    // UC16.4: Xóa loại thiên tai
    @DeleteMapping("/{typeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteType(@PathVariable Long typeId){
        disasterService.deleteType(typeId);
        return ResponseEntity.noContent().build();
    }
}

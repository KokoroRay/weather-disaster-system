package com.wds.disaster_service.controller;


import com.wds.disaster_service.dto.request.DisasterIngestRequest;
import com.wds.disaster_service.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/disaster")
@RequiredArgsConstructor
public class InternalDisasterController {

    private final DisasterService disasterService;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestBody DisasterIngestRequest req){
        disasterService.ingestFromN8n(req);
        return ResponseEntity.ok("Received");
    }
}

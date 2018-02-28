package com.urbanpawel.overtime.overtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overtime")
class OvertimeEndpoint {
    private final OvertimeService overtimeService;

    @Autowired
    public OvertimeEndpoint(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @PostMapping
    public ResponseEntity reportOvertime(@RequestBody ReportOvertimeDto request) {
        overtimeService.forDate(request.getDate()).hours(request.getHours()).save();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<OvertimeSummary> listOvertimes() {
        return overtimeService.summaries();
    }
}

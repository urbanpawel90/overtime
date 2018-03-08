package com.urbanpawel.overtime.overtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overtime")
class OvertimeController {
    private final OvertimeService overtimeService;

    @Autowired
    public OvertimeController(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @PostMapping
    public ResponseEntity reportOvertime(@RequestBody ReportOvertimeDto request) {
        overtimeService.reportForDate(request.getDate())
                .hours(request.getHours())
                .comment(request.getComment())
                .save();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<OvertimeSummary> listOvertimes() {
        return overtimeService.getSummaries();
    }
}

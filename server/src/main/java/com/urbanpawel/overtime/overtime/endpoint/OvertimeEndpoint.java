package com.urbanpawel.overtime.overtime.endpoint;

import com.urbanpawel.overtime.overtime.OvertimeService;
import com.urbanpawel.overtime.overtime.OvertimeSummary;
import org.springframework.beans.factory.annotation.Autowired;
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
        overtimeService.forDate(request.date()).hours(request.hours().intValue()).save();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<OvertimeSummary> listOvertimes() {
        return overtimeService.sumarries();
    }
}

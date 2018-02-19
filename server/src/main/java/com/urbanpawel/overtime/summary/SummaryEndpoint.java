package com.urbanpawel.overtime.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
class SummaryEndpoint {
    private final SummaryService summaryService;

    @Autowired
    SummaryEndpoint(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping
    public SummaryDto getSummary() {
        return summaryService.countSummary();
    }
}

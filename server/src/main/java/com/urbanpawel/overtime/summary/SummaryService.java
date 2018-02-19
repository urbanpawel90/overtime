package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class SummaryService {
    private final DateTimeService dateTimeService;
    private final OvertimeByDateRepository overtimeRepository;

    @Autowired
    SummaryService(OvertimeByDateRepository overtimeRepository, DateTimeService dateTimeService) {
        this.overtimeRepository = overtimeRepository;
        this.dateTimeService = dateTimeService;
    }

    public SummaryDto countSummary() {
        return overtimeRepository.getOvertimesGroupedByDate().stream()
                .reduce(SummaryDto.empty(),
                        (sum, item) -> sum.applyItem(dateTimeService, item),
                        SummaryDto::combine);
    }

}

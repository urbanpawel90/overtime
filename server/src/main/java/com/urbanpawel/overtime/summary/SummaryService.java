package com.urbanpawel.overtime.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class SummaryService {
    private final OvertimeByDateRepository overtimeRepository;

    @Autowired
    SummaryService(OvertimeByDateRepository overtimeRepository) {
        this.overtimeRepository = overtimeRepository;
    }

    public SummaryDto countSummary() {
        return overtimeRepository.getOvertimesGroupedByDate().stream()
                .reduce(SummaryDto.empty(),
                        SummaryDto::applyItem,
                        SummaryDto::combine);
    }

}

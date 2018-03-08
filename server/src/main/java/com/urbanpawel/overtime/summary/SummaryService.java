package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class SummaryService {
    private final DateTimeUtils dateTimeUtils;
    private final OvertimeByDateRepository overtimeRepository;

    @Autowired
    SummaryService(OvertimeByDateRepository overtimeRepository, DateTimeUtils dateTimeUtils) {
        this.overtimeRepository = overtimeRepository;
        this.dateTimeUtils = dateTimeUtils;
    }

    public SummaryDto countSummary() {
        return overtimeRepository.getOvertimesGroupedByDate().stream()
                .reduce(SummaryDto.empty(),
                        (sum, item) -> sum.applyItem(dateTimeUtils, item),
                        SummaryDto::combine);
    }

}

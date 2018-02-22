package com.urbanpawel.overtime.summary;

import java.util.Set;

interface OvertimeByDateRepository {
    Set<SummaryItem> getOvertimesGroupedByDate();
}

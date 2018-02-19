package com.urbanpawel.overtime.summary;

import java.util.Set;

public interface OvertimeByDateRepository {
    Set<SummaryItem> getOvertimesGroupedByDate();
}

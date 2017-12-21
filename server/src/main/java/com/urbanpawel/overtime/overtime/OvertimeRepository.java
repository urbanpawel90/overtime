package com.urbanpawel.overtime.overtime;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface OvertimeRepository {
    Optional<OvertimeSummary> summaryFor(LocalDate date);

    void saveSummary(OvertimeSummary summary);

    List<OvertimeSummary> allSummaries();
}

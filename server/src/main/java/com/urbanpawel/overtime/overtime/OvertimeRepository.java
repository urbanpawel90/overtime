package com.urbanpawel.overtime.overtime;

import java.time.LocalDate;
import java.util.Optional;

public interface OvertimeRepository {
    Optional<OvertimeSummary> summaryFor(LocalDate date);

    void saveSummary(OvertimeSummary summary);
}

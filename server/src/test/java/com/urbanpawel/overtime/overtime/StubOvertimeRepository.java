package com.urbanpawel.overtime.overtime;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StubOvertimeRepository implements OvertimeRepository {
    private final Map<LocalDate, OvertimeSummary> entries = new HashMap<>();

    @Override
    public Optional<OvertimeSummary> summaryFor(LocalDate date) {
        return Optional.ofNullable(entries.get(date));
    }

    @Override
    public void update(OvertimeSummary summary) {
        entries.put(summary.getDate(), summary);
    }

    @Override
    public void create(OvertimeSummary newSummary) {
        entries.putIfAbsent(newSummary.getDate(), newSummary);
    }
}

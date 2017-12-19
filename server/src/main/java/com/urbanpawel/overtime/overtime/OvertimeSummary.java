package com.urbanpawel.overtime.overtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OvertimeSummary {
    private final BigDecimal hours;
    private final LocalDate date;
    private final List<Change> changes;

    public static OvertimeSummary fromChange(Change change) {
        return new OvertimeSummary(change.amount, change.when.toLocalDate(), Collections.singletonList(change));
    }

    OvertimeSummary(BigDecimal hours, LocalDate date) {
        this(hours, date, Collections.emptyList());
    }

    OvertimeSummary(BigDecimal hours, LocalDate date, List<Change> changes) {
        this.hours = hours;
        this.date = date;
        this.changes = Collections.unmodifiableList(changes);
    }

    public static OvertimeSummary emptySummary(LocalDate date) {
        return new OvertimeSummary(BigDecimal.ZERO, date);
    }

    public BigDecimal getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Change> getChanges() {
        return changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OvertimeSummary)) return false;
        OvertimeSummary that = (OvertimeSummary) o;
        return Objects.equals(hours, that.hours) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, date);
    }

    public OvertimeSummary apply(Change change) {
        return new OvertimeSummary(hours.add(change.amount), date, Stream.concat(Stream.of(change), changes.stream())
                .collect(Collectors.toList()));
    }

    public static class Change {
        private final BigDecimal amount;
        private final ZonedDateTime when;

        public Change(BigDecimal amount, ZonedDateTime when) {
            this.amount = amount;
            this.when = when;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public ZonedDateTime getWhen() {
            return when;
        }
    }
}

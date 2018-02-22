package com.urbanpawel.overtime.overtime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "overtime")
public final class OvertimeSummary {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "`date`", nullable = false, unique = true)
    private LocalDate date;
    @Column(nullable = false)
    private BigDecimal hours;
    @ElementCollection
    private List<Change> changes;

    @SuppressWarnings("unused")
    private OvertimeSummary() {
    }

    private OvertimeSummary(LocalDate date) {
        this(BigDecimal.ZERO, date, Collections.emptyList());
    }

    OvertimeSummary(BigDecimal hours, LocalDate date, List<Change> changes) {
        this.hours = hours;
        this.date = date;
        this.changes = Collections.unmodifiableList(changes);
    }

    private OvertimeSummary(Integer id, BigDecimal hours, LocalDate date, List<Change> changes) {
        this(hours, date, changes);
        this.id = id;
    }

    public static OvertimeSummary emptySummary(LocalDate date) {
        return new OvertimeSummary(date);
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

    public OvertimeSummary apply(OvertimeSummary change) {
        return new OvertimeSummary(id, hours.add(change.getHours()), date,
                Stream.concat(change.changes.stream(), changes.stream())
                        .sorted(Comparator.comparing(Change::getWhen).reversed())
                        .collect(Collectors.toList()));
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

    @Embeddable
    public static class Change {
        private BigDecimal amount;
        @Column(name = "`when`")
        private LocalDateTime when;

        @SuppressWarnings("unused")
        Change() {
        }

        Change(BigDecimal amount, LocalDateTime when) {
            this.amount = amount;
            this.when = when;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        LocalDateTime getWhen() {
            return when;
        }
    }
}

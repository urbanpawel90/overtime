package com.urbanpawel.overtime.overtime;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"date", "hours"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @Tolerate
    private OvertimeSummary(LocalDate date) {
        this(BigDecimal.ZERO, date, Collections.emptyList());
    }

    @Tolerate
    OvertimeSummary(BigDecimal hours, LocalDate date, List<Change> changes) {
        this(null, date, hours, changes);
    }

    public static OvertimeSummary emptySummary(LocalDate date) {
        return new OvertimeSummary(date);
    }

    public OvertimeSummary apply(OvertimeSummary change) {
        return new OvertimeSummary(id, date, hours.add(change.getHours()),
                Stream.concat(change.changes.stream(), changes.stream())
                        .sorted(Comparator.comparing(Change::getWhen).reversed())
                        .collect(Collectors.toList()));
    }

    @Data
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    static class Change {
        private BigDecimal amount;
        @Column(name = "`when`")
        private LocalDateTime when;
        private String comment;
    }
}

package com.urbanpawel.overtime.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.urbanpawel.overtime.DateTimeService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@AutoValue
abstract class SummaryDto {
    static SummaryDto create(BigDecimal total, BigDecimal week, BigDecimal month) {
        return new AutoValue_SummaryDto(total, week, month);
    }

    static SummaryDto empty() {
        return create(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    private static BigDecimal sumConditionally(BigDecimal lhs, BigDecimal rhs, BooleanSupplier test) {
        return Optional.of(lhs)
                .filter(__ -> test.getAsBoolean())
                .map(current -> current.add(rhs))
                .orElse(lhs);
    }

    @JsonProperty("total")
    abstract BigDecimal total();

    @JsonProperty("week")
    abstract BigDecimal week();

    @JsonProperty("month")
    abstract BigDecimal month();

    public SummaryDto applyItem(DateTimeService dateTimeService, SummaryItem item) {
        return create(total().add(item.hours),
                sumConditionally(week(), item.hours, () -> dateTimeService.isCurrentWeek(item.date)),
                sumConditionally(month(), item.hours, () -> dateTimeService.isCurrentMonth(item.date)));
    }

    public SummaryDto combine(SummaryDto second) {
        return create(total().add(second.total()),
                week().add(second.week()),
                month().add(second.month()));
    }
}

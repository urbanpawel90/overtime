package com.urbanpawel.overtime.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.urbanpawel.overtime.DateTimeService;

import java.math.BigDecimal;
import java.util.function.BooleanSupplier;

@AutoValue
abstract class SummaryDto {
    static SummaryDto create(BigDecimal total, BigDecimal week, BigDecimal month) {
        return new AutoValue_SummaryDto(total, week, month);
    }

    static SummaryDto empty() {
        return create(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    private static BigDecimal incrementConditionally(BigDecimal value, BooleanSupplier test) {
        if (test.getAsBoolean()) {
            return value.add(BigDecimal.ONE);
        }
        return value;
    }

    @JsonProperty("total")
    abstract BigDecimal total();

    @JsonProperty("week")
    abstract BigDecimal week();

    @JsonProperty("month")
    abstract BigDecimal month();

    public SummaryDto applyItem(DateTimeService dateTimeService, SummaryItem item) {
        return create(total().add(item.hours),
                incrementConditionally(week(), () -> dateTimeService.isCurrentWeek(item.date)),
                incrementConditionally(month(), () -> dateTimeService.isCurrentMonth(item.date)));
    }

    public SummaryDto combine(SummaryDto second) {
        return create(total().add(second.total()),
                week().add(second.week()),
                month().add(second.month()));
    }
}

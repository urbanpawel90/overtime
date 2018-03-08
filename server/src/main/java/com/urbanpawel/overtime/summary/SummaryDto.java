package com.urbanpawel.overtime.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urbanpawel.overtime.DateTimeUtils;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Value(staticConstructor = "create")
class SummaryDto {
    @JsonProperty("total")
    BigDecimal total;
    @JsonProperty("week")
    BigDecimal week;
    @JsonProperty("month")
    BigDecimal month;

    static SummaryDto empty() {
        return create(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    private static BigDecimal sumConditionally(BigDecimal lhs, BigDecimal rhs, BooleanSupplier test) {
        return Optional.of(lhs)
                .filter(__ -> test.getAsBoolean())
                .map(current -> current.add(rhs))
                .orElse(lhs);
    }

    public SummaryDto applyItem(DateTimeUtils dateTimeUtils, SummaryItem item) {
        return create(total.add(item.hours),
                sumConditionally(week, item.hours, () -> dateTimeUtils.isCurrentWeek(item.date)),
                sumConditionally(month, item.hours, () -> dateTimeUtils.isCurrentMonth(item.date)));
    }

    public SummaryDto combine(SummaryDto second) {
        return create(total.add(second.total),
                week.add(second.week),
                month.add(second.month));
    }
}

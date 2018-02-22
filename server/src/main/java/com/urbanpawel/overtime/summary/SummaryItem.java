package com.urbanpawel.overtime.summary;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SummaryItem {
    final LocalDate date;
    final BigDecimal hours;

    SummaryItem(LocalDate date, BigDecimal hours) {
        this.date = date;
        this.hours = hours;
    }
}

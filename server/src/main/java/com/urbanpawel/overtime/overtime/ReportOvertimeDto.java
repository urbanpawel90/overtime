package com.urbanpawel.overtime.overtime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.math.BigDecimal;
import java.time.LocalDate;

@AutoValue
abstract class ReportOvertimeDto {
    @JsonCreator
    @SuppressWarnings("unused")
    public static ReportOvertimeDto create(@JsonProperty("date") LocalDate date, @JsonProperty("hours") BigDecimal hours) {
        return new AutoValue_ReportOvertimeDto(date, hours);
    }

    abstract LocalDate date();

    abstract BigDecimal hours();
}

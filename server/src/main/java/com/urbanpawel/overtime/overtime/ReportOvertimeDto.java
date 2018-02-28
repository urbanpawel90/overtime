package com.urbanpawel.overtime.overtime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ReportOvertimeDto {
    LocalDate date;
    BigDecimal hours;

    @JsonCreator
    @SuppressWarnings("unused")
    public static ReportOvertimeDto create(@JsonProperty("date") LocalDate date, @JsonProperty("hours") BigDecimal hours) {
        return new ReportOvertimeDto(date, hours);
    }
}

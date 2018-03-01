package com.urbanpawel.overtime.overtime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
final class ReportOvertimeDto {
    private LocalDate date;
    private BigDecimal hours;
    private String comment;
}

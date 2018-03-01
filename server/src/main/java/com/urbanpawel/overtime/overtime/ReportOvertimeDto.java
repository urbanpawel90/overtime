package com.urbanpawel.overtime.overtime;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
final class ReportOvertimeDto {
    LocalDate date;
    BigDecimal hours;
}

package com.urbanpawel.overtime.overtime;

import lombok.*;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
final class ReportOvertimeDto {
    private LocalDate date;
    private BigDecimal hours;
    private String comment;

    @Tolerate
    public ReportOvertimeDto(LocalDate date, BigDecimal hours) {
        this(date, hours, null);
    }
}

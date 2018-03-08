package com.urbanpawel.overtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

public class DateTimeUtils {
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static boolean isCurrentWeek(LocalDate date) {
        return now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public static boolean isCurrentMonth(LocalDate date) {
        return now().getMonth() == date.getMonth();
    }
}

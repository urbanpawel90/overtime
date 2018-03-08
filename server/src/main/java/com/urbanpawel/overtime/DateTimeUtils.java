package com.urbanpawel.overtime;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

// TODO: DateTimeService statycznie i tool do mockowania metod statycznych
@Component
public class DateTimeUtils {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public boolean isCurrentWeek(LocalDate date) {
        return now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public boolean isCurrentMonth(LocalDate date) {
        return now().getMonth() == date.getMonth();
    }
}

package com.urbanpawel.overtime;

import java.time.LocalDateTime;
import java.time.Month;

public class FakeDateTimeUtils extends DateTimeUtils {
    private final LocalDateTime fixedNow;

    private FakeDateTimeUtils(LocalDateTime fixedNow) {
        this.fixedNow = fixedNow;
    }

    public static DateTimeUtils fake2018Valentines2pm() {
        return new FakeDateTimeUtils(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }

    @Override
    public LocalDateTime now() {
        return fixedNow;
    }
}

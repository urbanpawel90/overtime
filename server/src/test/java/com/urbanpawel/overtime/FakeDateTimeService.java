package com.urbanpawel.overtime;

import java.time.LocalDateTime;
import java.time.Month;

public class FakeDateTimeService extends DateTimeService {
    private final LocalDateTime fixedNow;

    private FakeDateTimeService(LocalDateTime fixedNow) {
        this.fixedNow = fixedNow;
    }

    public static DateTimeService fake2018Valentines2pm() {
        return new FakeDateTimeService(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }

    @Override
    public LocalDateTime now() {
        return fixedNow;
    }
}

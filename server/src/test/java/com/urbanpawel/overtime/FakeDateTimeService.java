package com.urbanpawel.overtime;

import java.time.LocalDateTime;
import java.time.Month;

public class FakeDateTimeService extends DateTimeService {
    private final LocalDateTime fixedNow;

    public static DateTimeService fake2018Valentines2pm() {
        return new FakeDateTimeService(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }

    private FakeDateTimeService(LocalDateTime fixedNow) {
        this.fixedNow = fixedNow;
    }

    @Override
    protected LocalDateTime now() {
        return fixedNow;
    }
}

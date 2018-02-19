package com.urbanpawel.overtime;

import java.time.LocalDateTime;

public class FakeDateTimeService extends DateTimeService {
    private final LocalDateTime fixedNow;

    public FakeDateTimeService(LocalDateTime fixedNow) {
        this.fixedNow = fixedNow;
    }

    @Override
    protected LocalDateTime now() {
        return fixedNow;
    }
}
